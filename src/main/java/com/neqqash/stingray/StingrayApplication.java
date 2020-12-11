package com.neqqash.stingray;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
@SpringBootApplication
public class StingrayApplication {

	public static void main(String[] args) {
		SpringApplication.run(StingrayApplication.class, args);
	}

}
