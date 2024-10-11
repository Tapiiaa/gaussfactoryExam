package com.example.gaussfactory.controller;

import com.example.gaussfactory.service.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/simulation")
@CrossOrigin(origins = "*")
public class SimulationController {

    private final SimulationService simulationService;

    @Autowired
    public SimulationController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @PostMapping("/start")
    public void startSimulation(@RequestParam int numberOfBalls, @RequestParam int numberOfLevels) {
        simulationService.startSimulation(numberOfBalls, numberOfLevels);
    }

    @PostMapping("/advance")
    public boolean advanceSimulation() {
        return simulationService.advanceOneLevel();
    }

    @GetMapping("/bins")
    public Map<Integer, Integer> getBins() {
        return simulationService.getBins();
    }

    @GetMapping("/running")
    public boolean isSimulationRunning() {
        return simulationService.isSimulationRunning();
    }
}
