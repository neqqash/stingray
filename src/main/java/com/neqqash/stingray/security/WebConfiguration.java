package com.neqqash.stingray.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;



@Configuration
public class WebConfiguration {

    @Bean
    public BasicAuthenticationEntryPoint basicAuthenticationEntryPoint(){
        return new BasicAuth();
    }
}
