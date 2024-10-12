package com.example.gaussfactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.HistogramDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GaussChart extends JFrame {

    // Constructor que recibe el título y los datos para la visualización
    public GaussChart(String title, List<Double> data) {
        super(title);

        // Crear el dataset para el gráfico de la campana de Gauss
        HistogramDataset dataset = createDataset(data);

        // Crear el gráfico de la campana de Gauss usando JFreeChart
        JFreeChart chart = ChartFactory.createHistogram(
                "Campana de Gauss",
                "Valores",
                "Frecuencia",
                dataset
        );

        // Crear un panel para el gráfico
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));

        // Agregar el panel a la ventana
        setContentPane(chartPanel);

        // Configuración básica de la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);  // Centrar la ventana en la pantalla
        this.setVisible(true);
    }

    // Método para crear el dataset del histograma
    private HistogramDataset createDataset(List<Double> data) {
        HistogramDataset dataset = new HistogramDataset();
        double[] values = data.stream().mapToDouble(Double::doubleValue).toArray();
        int numberOfBins = 50; // Ajustar el número de bins (intervalos)
        dataset.addSeries("Distribución", values, numberOfBins);
        return dataset;
    }

    // Método para mostrar el gráfico
    public static void showGaussChart(List<Double> data) {
        SwingUtilities.invokeLater(() -> {
            new GaussChart("Visualización de Campana de Gauss", data);
        });
    }
}
