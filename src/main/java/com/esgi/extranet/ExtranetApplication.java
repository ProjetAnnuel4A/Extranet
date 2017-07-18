package com.esgi.extranet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@org.springframework.context.annotation.Configuration
@SpringBootApplication
@EnableAutoConfiguration
@EnableTransactionManagement
public class ExtranetApplication {
    //commit pour l'intégration continue
	public static void main(String[] args) {
		SpringApplication.run(ExtranetApplication.class, args);
	}
}
