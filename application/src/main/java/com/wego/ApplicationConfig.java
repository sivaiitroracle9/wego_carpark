package com.wego;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(HibernateConfiguration.class)
public class ApplicationConfig {

}
