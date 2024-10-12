package com.example.gaussfactory.controller;
import com.example.gaussfactory.model.Ball;
import com.example.gaussfactory.service.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        simulationService.setSimulationData(data);
    }

    public void startSimulation(){
        simulationService.startSimulation();
    }

    public List<Double> getBallPositions(){
        return simulationService.getBallPositions();
    }

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

    public void startSimulationStep(){  // Método para avanzar un paso en la simulación y que no sea todo de golpe
        if(!simulationService.getBalls().isEmpty()){
            Ball ball = simulationService.getBalls().get(0);
            ball.fall();
        }
    }

    public int getBallsLeft(){
        return simulationService.getBalls().size();
    }
}
