package com.smart.sotral;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SotralApplication {

	public static void main(String[] args) {
		SpringApplication.run(SotralApplication.class, args);
	}

}
