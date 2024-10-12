package com.example.gaussfactory.controller;
import com.example.gaussfactory.service.SimulationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SimulationController {

    private SimulationService simulationService;

    // Constructor
    public SimulationController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    // Método para devolver todos los datos de la simulación en formato JSON
    @GetMapping("/simulation/data")
    public List<Double> getAllData() {
        return simulationService.getAllData();
    }
}
