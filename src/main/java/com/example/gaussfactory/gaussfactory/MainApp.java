package com.example.gaussfactory.gaussfactory;
import com.example.gaussfactory.service.DataLoader;
import com.example.gaussfactory.visualization.GaussChart;
import com.example.gaussfactory.controller.SimulationController;
import com.example.gaussfactory.service.SimulationService;

import java.io.IOException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        DataLoader loader = new DataLoader();
        try {
            // Cargar los datos desde el archivo CSV
            List<Double> data = loader.loadData("src/main/resources/CompanyABCProfit.csv");

            // Verificar que los datos se han cargado correctamente
            System.out.println("Datos cargados: " + data);  // Imprimir los datos para depuración

            // Crear un objeto de servicio de simulación con los datos
            SimulationService simulationService = new SimulationService(data);
            SimulationController simulationController = new SimulationController(simulationService);

            // Mostrar el gráfico de la campana de Gauss usando Swing
            GaussChart.showGaussChart(simulationController.getAllData());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
