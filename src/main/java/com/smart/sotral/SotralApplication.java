package com.smart.sotral;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@EnableScheduling
@OpenAPIDefinition(
    info = @Info(title = "SOTRAL API", version = "1.0",
                 description = "Système d'Information Voyageurs — Lomé, Togo"),
    servers = @Server(url = "http://192.168.210.216:8080")
)
public class SotralApplication {

	public static void main(String[] args) {
		SpringApplication.run(SotralApplication.class, args);
	}

}
