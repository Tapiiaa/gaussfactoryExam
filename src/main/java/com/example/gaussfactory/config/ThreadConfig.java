package com.example.gaussfactory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

@Configuration
public class ThreadConfig {

    @Bean
    public ExecutorService executorService(){
        return Executors.newFixedThreadPool(5); //Establecemos un pool con 5 hilos concurrentes.
    }
}
