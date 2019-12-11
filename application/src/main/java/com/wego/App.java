package com.wego;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@Import(value = { ApplicationConfig.class, SwaggerConfig.class })
@EnableAutoConfiguration
@EnableWebMvc
@EnableTransactionManagement
public class App
{
	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
	}
}
