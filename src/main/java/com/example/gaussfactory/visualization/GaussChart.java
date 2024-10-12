package com.example.gaussfactory.visualization;
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
    private List<Double> data;
    private List<Double> currentData;  // Para almacenar los datos a medida que caen
    private ChartPanel chartPanel;

    // Constructor que recibe el título y los datos para la visualización
    public GaussChart(String title, List<Double> data) {
        super(title);

        this.data = data;
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
            if (!data.isEmpty()) {
                // Agregar una bola a la vez desde los datos originales
                currentData.add(data.remove(0));

                // Actualizar el dataset y refrescar el gráfico
                updateDataset();
            } else {
                // Parar el timer cuando todas las bolas hayan caído
                ((Timer) e.getSource()).stop();
            }
        });

        timer.start();  // Iniciar el timer que actualiza el gráfico en intervalos
    }

    // Método para mostrar el gráfico
    public static void showGaussChart(List<Double> data) {
        SwingUtilities.invokeLater(() -> {
            GaussChart chart = new GaussChart("Visualización de Campana de Gauss (Tiempo Real)", data);
            chart.pack();
            chart.setVisible(true);
        });
    }
}

