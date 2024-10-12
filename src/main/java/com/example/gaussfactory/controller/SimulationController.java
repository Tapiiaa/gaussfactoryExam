package com.example.gaussfactory.controller;
import com.example.gaussfactory.service.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * Clase controladora de la simulación.
 * @version 1.0, 16/06/2021
 * @autor Pedro Alonso Tapia Lobo
 * @autor Patrik Paul Sirbu
 */
@RestController
public class SimulationController {

    private final SimulationService simulationService;

    /**
     * Constructor de la clase SimulationController.
     * @param simulationService Servicio de la simulación.
     */
    @Autowired
    public SimulationController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    public void initializeSimulation(List<Double> data){
        simulationService.initializeBalls(data.size());
        simulationService.setSimulationData(data);
    }
    /**
     * Método para iniciar la simulación.
     */
    public void startSimulation(){
        simulationService.startSimulation();
    }
    /**
     * Método para detener la simulación.
     */
    public List<Double> getBallPositions(){
        return simulationService.getBallPositions();
    }
    /**
     * Método para detener la simulación.
     */
    public void setSimulationSpeed(int speed){
        simulationService.setSimulationSpeed(speed);
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
