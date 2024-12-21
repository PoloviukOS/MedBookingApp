package com.project.rest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
	info = @Info(
		title = "Doctors schedule",
		description = "Creating a doctor's schedule and booking tickets",
		version = "0.0.1",
		contact = @Contact(
			name = "Poloviuk Oleh",
			email = "000055555w@gmail.com",
			url = "https://github.com/PoloviukOS"
		)
	)
)
public class RestServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(RestServerApplication.class, args);
	}
}
