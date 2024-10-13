package com.example.gaussfactory.config;

import com.example.gaussfactory.service.WorkStationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Clase de configuracion de la estacion de trabajo.
 * @version 1.0, 16/06/2021
 * @autor Pedro Alonso Tapia Lobo
 * @autor Patrik Paul Sirbu
 */
@Configuration
public class WorkStationConfig {
    @Bean
    public int maxComponents() {
        return 5;
    }
}
