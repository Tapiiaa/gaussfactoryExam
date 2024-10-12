package com.example.gaussfactory.config;

import com.example.gaussfactory.service.WorkStationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkStationConfig {
    @Bean
    public int maxComponents() {
        return 5;
    }
}
