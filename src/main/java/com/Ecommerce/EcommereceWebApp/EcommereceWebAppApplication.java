package com.Ecommerce.EcommereceWebApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.Ecommerce.EcommereceWebApp.entity.Users;

@SpringBootApplication
public class EcommereceWebAppApplication {

	public static void main(String[] args) {
		
		  ConfigurableApplicationContext context = SpringApplication.run(EcommereceWebAppApplication.class, args);

		}

}
