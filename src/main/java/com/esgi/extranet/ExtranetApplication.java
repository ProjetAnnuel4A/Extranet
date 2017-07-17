package com.esgi.extranet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@org.springframework.context.annotation.Configuration
@SpringBootApplication
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableWebMvc
public class ExtranetApplication extends WebMvcAutoConfiguration {
	public static void main(String[] args) {
		SpringApplication.run(ExtranetApplication.class, args);
	}
}
