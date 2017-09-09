package com.dvsmedeiros.ecommerce.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.dvsmedeiros.bce.core.BceCoreApplication;

@Import(BceCoreApplication.class)
@ComponentScan("com.dvsmedeiros")
@EnableAutoConfiguration
@SpringBootApplication
public class EcommerceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApiApplication.class, args);
	}
}
