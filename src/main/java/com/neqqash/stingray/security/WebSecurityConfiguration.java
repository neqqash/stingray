package com.neqqash.stingray.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
    private final BasicAuthenticationEntryPoint basicAuthEntryPoint;

    @Autowired
    public WebSecurityConfiguration(BasicAuthenticationEntryPoint basicAuthEntryPoint) {
        this.basicAuthEntryPoint = basicAuthEntryPoint;
    }


    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth, DataSource dataSource) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT username, password, true from user where username=?")
                .authoritiesByUsernameQuery("SELECT username, role from user where username=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .and()
                .authorizeRequests()
                .antMatchers("/")
                .hasAnyAuthority("admin", "client")
                .antMatchers("/admin/**")
                .hasAuthority("admin")
                .anyRequest()
                .authenticated()
                .and()
                .logout()
                .permitAll()
                .and()
                .httpBasic()
                .authenticationEntryPoint(basicAuthEntryPoint);
    }

}
