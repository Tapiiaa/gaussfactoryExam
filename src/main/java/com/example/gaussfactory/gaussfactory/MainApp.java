package com.example.gaussfactory.gaussfactory;
import com.example.gaussfactory.service.DataLoader;
import com.example.gaussfactory.visualization.GaussChart;
import com.example.gaussfactory.controller.SimulationController;
import com.example.gaussfactory.service.SimulationService;
import com.google.auto.value.AutoAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class MainApp implements CommandLineRunner {

    private final SimulationController simulationController;
    private final DataLoader dataLoader;

    @Autowired
    public MainApp(SimulationController simulationController, DataLoader dataLoader) {
        this.simulationController = simulationController;
        this.dataLoader = dataLoader;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            // Cargar los datos desde el archivo CSV
            List<Double> data = dataLoader.loadData("src/main/resources/CompanyABCProfit.csv");

            // Verificar que los datos se han cargado correctamente
            System.out.println("Datos cargados: " + data);  // Imprimir los datos para depuración

            // Crear un objeto de servicio de simulación con los datos
            simulationController.initializeSimulation(data);
            simulationController.startSimulation();

            // Mostrar el gráfico de la campana de Gauss usando Swing
            GaussChart.showGaussChart(simulationController.getAllData());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
