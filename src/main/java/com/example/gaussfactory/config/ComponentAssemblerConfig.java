package com.example.gaussfactory.config;

import com.example.gaussfactory.model.Component;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Configuration
public class ComponentAssemblerConfig {


    /**
     * Metodo que crea una cola bloqueante de componentes.
     * @return Cola bloqueante de componentes.
     * @author: Pedro Alonso Tapia Lobo
     * @author: Patrik Paul Sirbu
     */

    @Bean
    public BlockingQueue<Component> componentQueue() {
        return new ArrayBlockingQueue<>(5);
    }
}
