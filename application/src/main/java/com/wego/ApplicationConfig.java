package com.wego;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

@Configuration
@Import(HibernateConfiguration.class)
public class ApplicationConfig {

    @Bean
    public RestTemplate restTemplate()
    {

        return new RestTemplate();
    }

}
