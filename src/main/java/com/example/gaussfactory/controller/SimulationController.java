package com.example.gaussfactory.controller;
import com.example.gaussfactory.model.Ball;
import com.example.gaussfactory.service.SimulationService;
import com.example.gaussfactory.synchronization.SyncManager;
import com.google.cloud.firestore.Firestore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
public class SimulationController {

    private final SimulationService simulationService;
    private final SyncManager syncManager;
    private final Firestore firestore;

    // Constructor
    @Autowired
    public SimulationController(SimulationService simulationService, Firestore firestore) {
        this.simulationService = simulationService;
        this.syncManager = new SyncManager();
        this.firestore = firestore;
    }

    public void initializeSimulation(List<Double> data){
        syncManager.performSyncTask(() -> {
            simulationService.initializeBalls(data.size());
            simulationService.setSimulationData(data);
        });
    }

    @PostMapping("/simulation/start")
    public void startSimulation(@RequestBody Map<String, Object> simulationData){
        syncManager.performSyncTask(() -> {
            simulationService.startSimulation();

            // Crear un documento en Firestore para guardar los datos de la simulación
            Map<String, Object> docData = new HashMap<>();
            docData.put("status", "Simulation started");
            docData.put("data", simulationData);

            // Guardar el documento en la colección 'simulations' en Firestore
            try {
                firestore.collection("simulations").document().set(docData).get();  // Esto guarda los datos de la simulación en Firestore
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public List<Double> getBallPositions(){
        return simulationService.getBallPositions();
    }

    public void setSimulationSpeed(int speed){
        syncManager.performSyncTask(() -> {
            simulationService.setSimulationSpeed(speed);
        });
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
        syncManager.performSyncTask(() -> {
            if(!simulationService.getBalls().isEmpty()){
                Ball ball = simulationService.getBalls().get(0);
                ball.fall();
            }
        });
    }

    public int getBallsLeft(){
        return simulationService.getBalls().size();
    }
}
