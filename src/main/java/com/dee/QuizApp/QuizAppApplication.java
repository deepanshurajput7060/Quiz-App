package com.dee.QuizApp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition (
		info = @Info(
				title = "Quiz-Application REST API Documentation",
				description = "Quiz Application Documentation",
				contact = @Contact(
						name = "Deepanshu Rajput",
						email = "dee@gmail.com")
				)
		)
public class QuizAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizAppApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper () {
		return new ModelMapper();
	}

}
