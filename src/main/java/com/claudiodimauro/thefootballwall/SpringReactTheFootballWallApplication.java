package com.claudiodimauro.thefootballwall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "The Football Wall", version = "v0.0.1", description = "Football players statistics based on EA Sports Fifa21"
				+ "| Developed by Claudio S. Di Mauro | www.claudiodimauro.it"), 
		servers = {
				@Server(url = "http://localhost:8080", description = "Local - Development Environment"),
				@Server(url = "tbd", description = "[TBD] Application Development"),
				@Server(url = "tbd", description = "[TBD] Prod")
		})
public class SpringReactTheFootballWallApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringReactTheFootballWallApplication.class, args);
	}

}
