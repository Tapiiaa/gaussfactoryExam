package com.example.gaussfactory.gaussfactory;

import com.example.gaussfactory.controller.SimulationController;
import com.example.gaussfactory.model.Ball;
import com.example.gaussfactory.service.SimulationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
/**
 * Clase principal de la aplicación.
 * @version 1.0, 16/06/2021
 * @autor Pedro Alonso Tapia Lobo
 * @autor Patrik Paul Sirbu
 */
@SpringBootApplication(scanBasePackages = "com.example.gaussfactory")
@SpringBootConfiguration
public class GaussfactoryExamApplication {
    /**
     * Método principal de la aplicación.
     * @param args
     */
    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        SpringApplication.run(GaussfactoryExamApplication.class, args);
    }
}
