package com.example.gaussfactory.controller;
import com.example.gaussfactory.service.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SimulationController {

    private final SimulationService simulationService;

    // Constructor
    @Autowired
    public SimulationController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    public void initializeSimulation(List<Double> data){
        simulationService.initializeBalls(data.size());
    }

    public void startSimulation(){
        simulationService.startSimulation();
    }

    /**
     * Método para devolver todos los datos de la simulación en formato JSON.
     * Este método será expuesto como un endpoint REST en /simulation/data.
     *
     * @return Lista de datos de la simulación en formato JSON.
     */
    @GetMapping("/simulation/data")
    public List<Double> getAllData() {
        return simulationService.getAllData();
    }
}
