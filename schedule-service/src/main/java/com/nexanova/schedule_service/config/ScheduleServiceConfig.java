package com.nexanova.schedule_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ScheduleServiceConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}