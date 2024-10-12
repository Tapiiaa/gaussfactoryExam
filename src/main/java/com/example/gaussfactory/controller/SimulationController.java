package com.example.gaussfactory.controller;

import com.example.gaussfactory.service.SimulationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimulationController {

    private SimulationService simulationService;

    // Inicializar el servicio con los datos cargados
    public SimulationController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    // Endpoint para iniciar la simulación
    @PostMapping("/simulation/start")
    public void startSimulation() {
        // Aquí podrías inicializar la simulación si es necesario
    }

    // Endpoint para obtener los valores actualizados de la simulación
    @GetMapping("/simulation/bins")
    public double getSimulationValue() {
        return simulationService.getNextValue();
    }

    // Avanzar la simulación
    @PostMapping("/simulation/advance")
    public boolean advanceSimulation() {
        // Avanza la simulación y devuelve si está corriendo
        simulationService.getNextValue();
        return true; // Aquí puedes agregar lógica para controlar si la simulación sigue corriendo
    }
}
