package com.example.gaussfactory.gaussfactory;
import com.example.gaussfactory.service.SimulationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
public class GaussfactoryExamApplication extends JFrame {

    private SimulationService simulationService;
    private JPanel graphPanel;

    public static void main(String[] args) {
        SpringApplication.run(GaussfactoryExamApplication.class, args);
        SwingUtilities.invokeLater(() -> {
            GaussfactoryExamApplication app = new GaussfactoryExamApplication();
            app.setVisible(true);
        });
    }

    public GaussfactoryExamApplication() {
        // Configurar la ventana
        setTitle("Simulación de Campana de Gauss");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configurar el panel de gráficos
        graphPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawGraph(g);
            }
        };
        graphPanel.setBackground(Color.WHITE);
        add(graphPanel);

        // Inicializar la simulación
        simulationService = new SimulationService();
        simulationService.startSimulation(1000, 10); // Iniciar la simulación con 1000 bolas y 10 niveles

        // Configurar un Timer para actualizar la gráfica periódicamente
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateGraph();
            }
        }, 0, 500); // Actualizar cada 500 ms
    }

    // Método para actualizar la gráfica en el panel
    private void updateGraph() {
        simulationService.advanceSimulation();
        graphPanel.repaint();
    }

    // Método para dibujar el gráfico en el panel
    private void drawGraph(Graphics g) {
        Map<Integer, Integer> bins = simulationService.getCurrentBins();

        int panelWidth = graphPanel.getWidth();
        int panelHeight = graphPanel.getHeight();
        int binWidth = panelWidth / bins.size();

        // Encontrar el valor máximo para escalar las barras
        int maxCount = bins.values().stream().max(Integer::compare).orElse(1);

        // Dibujar cada barra
        int x = 0;
        for (Map.Entry<Integer, Integer> entry : bins.entrySet()) {
            int count = entry.getValue();
            int barHeight = (int) ((double) count / maxCount * panelHeight);

            g.setColor(Color.BLUE);
            g.fillRect(x, panelHeight - barHeight, binWidth, barHeight);

            g.setColor(Color.BLACK);
            g.drawRect(x, panelHeight - barHeight, binWidth, barHeight);

            x += binWidth;
        }
    }
}
