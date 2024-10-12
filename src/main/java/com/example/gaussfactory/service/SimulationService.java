package com.example.gaussfactory.service;
import java.util.List;

public class SimulationService {

    private List<Double> data;

    // Constructor que recibe los datos de la simulación
    public SimulationService(List<Double> data) {
        this.data = data;
    }

    // Método para obtener todos los datos de la simulación
    public List<Double> getAllData() {
        return this.data;
    }
}

