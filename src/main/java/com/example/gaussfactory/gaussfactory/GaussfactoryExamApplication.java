package com.example.gaussfactory.gaussfactory;
import com.example.gaussfactory.service.SimulationService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Map;

public class GaussfactoryExamApplication extends JFrame {

    private SimulationService simulationService;
    private JPanel graphPanel;
    private JSlider ballsSlider, levelsSlider;
    private JLabel ballsLabel, levelsLabel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GaussfactoryExamApplication app = new GaussfactoryExamApplication();
            app.setVisible(true);
        });
    }

    public GaussfactoryExamApplication() {
        // Initialize window
        setTitle("Gaussian Bell Simulation");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create sliders and labels for user interaction
        ballsLabel = new JLabel("Number of Balls: 1000");
        levelsLabel = new JLabel("Number of Levels: 10");
        ballsSlider = new JSlider(100, 5000, 1000);
        levelsSlider = new JSlider(2, 20, 10);

        ballsSlider.addChangeListener(e -> {
            ballsLabel.setText("Number of Balls: " + ballsSlider.getValue());
            restartSimulation();
        });

        levelsSlider.addChangeListener(e -> {
            levelsLabel.setText("Number of Levels: " + levelsSlider.getValue());
            restartSimulation();
        });

        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new GridLayout(2, 2));
        controlsPanel.add(ballsLabel);
        controlsPanel.add(ballsSlider);
        controlsPanel.add(levelsLabel);
        controlsPanel.add(levelsSlider);

        // Add the control panel and graph panel
        graphPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawGraph(g);
            }
        };
        graphPanel.setBackground(Color.WHITE);

        setLayout(new BorderLayout());
        add(controlsPanel, BorderLayout.NORTH);
        add(graphPanel, BorderLayout.CENTER);

        // Initialize the simulation
        simulationService = new SimulationService();
        restartSimulation();

        // Timer to update the graph in real-time
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateGraph();
            }
        }, 0, 500); // Update every 500 ms
    }

    // Restart the simulation with new parameters
    private void restartSimulation() {
        simulationService.startSimulation(ballsSlider.getValue(), levelsSlider.getValue());
    }

    // Update the graph in the panel
    private void updateGraph() {
        simulationService.advanceSimulation();
        graphPanel.repaint();
    }

    // Draw the graph on the panel
    private void drawGraph(Graphics g) {
        Map<Integer, Integer> bins = simulationService.getCurrentBins();

        int panelWidth = graphPanel.getWidth();
        int panelHeight = graphPanel.getHeight();
        int binWidth = panelWidth / bins.size();

        // Draw X and Y axes
        g.setColor(Color.BLACK);
        g.drawLine(50, panelHeight - 50, panelWidth - 50, panelHeight - 50); // X axis
        g.drawLine(50, panelHeight - 50, 50, 50); // Y axis

        // Find the maximum value to scale the bars
        int maxCount = bins.values().stream().max(Integer::compare).orElse(1);

        // Draw each point on the curve
        int previousX = -1, previousY = -1;
        int xPosition = 50;
        for (Map.Entry<Integer, Integer> entry : bins.entrySet()) {
            int count = entry.getValue();
            int barHeight = (int) ((double) count / maxCount * (panelHeight - 100));
            int yPosition = panelHeight - 50 - barHeight;

            g.setColor(Color.BLUE);
            g.fillOval(xPosition - 3, yPosition - 3, 6, 6); // Draw the curve point

            // Draw the line between points
            if (previousX != -1 && previousY != -1) {
                g.drawLine(previousX, previousY, xPosition, yPosition);
            }

            previousX = xPosition;
            previousY = yPosition;
            xPosition += binWidth;
        }

        // Labels for the axes
        g.setColor(Color.BLACK);
        g.drawString("Levels", panelWidth / 2, panelHeight - 20);
        g.drawString("Balls", 10, panelHeight / 2);
    }
}
