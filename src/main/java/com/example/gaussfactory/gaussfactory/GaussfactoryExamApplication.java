package com.example.gaussfactory.gaussfactory;

import com.example.gaussfactory.controller.SimulationController;
import com.example.gaussfactory.model.Ball;
import com.example.gaussfactory.service.SimulationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication(scanBasePackages = "com.example.gaussfactory")
public class GaussfactoryExamApplication {

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        SpringApplication.run(GaussfactoryExamApplication.class, args);
    }
}
