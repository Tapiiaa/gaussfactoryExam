package com.example.gaussfactory.gaussfactory;
import com.example.gaussfactory.controller.SimulationController;
import com.example.gaussfactory.model.Ball;
import com.example.gaussfactory.service.SimulationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class GaussfactoryExamApplication {

    public static void main(String[] args) {
        SpringApplication.run(GaussfactoryExamApplication.class, args);
    }

    public static void showGaussChart(List<Double> data) {
        // Crear un objeto de la clase Ball
        Ball ball = new Ball(data.size());

        // Crear un objeto de la clase SimulationService
        SimulationService simulationService = new SimulationService(data);

        // Crear un objeto de la clase SimulationController
        SimulationController simulationController = new SimulationController(simulationService);

        // Simular el movimiento de la bola por cada nivel
        for (int i = 0; i < data.size(); i++) {
            ball.move();
        }

        // Mostrar los datos de la simulación en formato JSON
        System.out.println(simulationController.getAllData());
    }
}
