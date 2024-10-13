package com.example.gaussfactory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

/**
 * Clase de configuracion de hilos.
 * @version 1.0, 16/06/2021
 * @autor Pedro Alonso Tapia Lobo
 * @autor Patrik Paul Sirbu
 */
@Configuration
public class ThreadConfig {

    /**
     * Metodo que crea un pool de hilos.
     * @return Pool de hilos.
     */
    @Bean
    public ExecutorService executorService(){
        return Executors.newFixedThreadPool(5); //Establecemos un pool con 5 hilos concurrentes.
    }
}
