package com.example.gaussfactory.controller;
import com.example.gaussfactory.service.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/simulation")
public class SimulationController {

    @Autowired
    private SimulationService simulationService;

    // Iniciar la simulación con el número de bolas y niveles especificados
    @PostMapping("/start")
    public void startSimulation(@RequestParam int numberOfBalls, @RequestParam int numberOfLevels) {
        simulationService.startSimulation(numberOfBalls, numberOfLevels);
    }

    // Avanzar la simulación en un paso
    @PostMapping("/advance")
    public boolean advanceSimulation() {
        return simulationService.advanceSimulation();
    }

    // Obtener los datos actuales de los contenedores
    @GetMapping("/bins")
    public Map<Integer, Integer> getBins() {
        return simulationService.getCurrentBins();
    }
}
