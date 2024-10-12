package com.example.gaussfactory.service;
import java.util.List;

public class SimulationService {

    private List<Double> data;
    private int currentIndex = 0;

    // Constructor que recibe los datos de la simulación
    public SimulationService(List<Double> data) {
        this.data = data;
    }

    // Método para avanzar en la simulación usando los datos del CSV
    public double getNextValue() {
        if (currentIndex < data.size()) {
            double value = data.get(currentIndex);
            currentIndex++;
            return value;
        } else {
            // Si llegamos al final de los datos, volver al principio
            currentIndex = 0;
            return data.get(currentIndex);
        }
    }
}
