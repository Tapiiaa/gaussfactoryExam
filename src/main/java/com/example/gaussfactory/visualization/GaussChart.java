package com.example.gaussfactory.visualization;
import com.example.gaussfactory.controller.SimulationController;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.HistogramDataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class GaussChart extends JFrame {

    private HistogramDataset dataset;
    private List<Double> currentData;  // Para almacenar los datos a medida que caen
    private ChartPanel chartPanel;
    private SimulationController simulationController;

    // Constructor que recibe el título y los datos para la visualización
    public GaussChart(String title, SimulationController simulationController) {
        super(title);
        this.simulationController = simulationController;
        this.currentData = new ArrayList<>();  // Inicialmente vacío


        // Crear el dataset con un valor mínimo para evitar el error inicial
        dataset = new HistogramDataset();
        dataset.addSeries("Distribución", new double[]{0}, 10);  // Iniciar con un valor vacío

        // Crear el gráfico de la campana de Gauss usando JFreeChart
        JFreeChart chart = ChartFactory.createHistogram(
                "Campana de Gauss (Tiempo Real)",
                "Valores",
                "Frecuencia",
                dataset
        );

        // Crear un panel para el gráfico
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));

        // Agregar el panel a la ventana
        setContentPane(chartPanel);

        // Configuración básica de la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);  // Centrar la ventana en la pantalla
        this.setVisible(true);

        // Iniciar la simulación de la caída de las bolas
        startSimulation();
    }

    // Método para crear o actualizar el dataset del histograma
    private void updateDataset() {
        List<Double> ballPositions = simulationController.getBallPositions();
        if (!currentData.isEmpty()) {
            dataset = new HistogramDataset();
            double[] values = currentData.stream().mapToDouble(Double::doubleValue).toArray();
            int numberOfBins = 50;  // Ajustar el número de bins (intervalos)
            dataset.addSeries("Distribución", values, numberOfBins);

            // Actualizar el gráfico en el panel
            chartPanel.getChart().getXYPlot().setDataset(dataset);
        }
    }

    // Método para iniciar la simulación de la caída de las bolas
    private void startSimulation() {
        Timer timer = new Timer(100, e -> {
            if (simulationController.getBallsLeft() > 0) {
                currentData.add(simulationController.getBallPositions().get(0));
                simulationController.startSimulationStep();
                updateDataset();
            } else {
                ((Timer) e.getSource()).stop();
            }
        });
        timer.start();  // Iniciar el timer que actualiza el gráfico en intervalos
    }

    // Método para mostrar el gráfico
    public static void showGaussChart(SimulationController simulationController ) {
        SwingUtilities.invokeLater(() -> {
            GaussChart chart = new GaussChart("Visualización de Campana de Gauss (Tiempo Real)", simulationController);
            chart.pack();
            chart.setVisible(true);
        });
    }
}