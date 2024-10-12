package com.example.gaussfactory.gaussfactory;
import com.example.gaussfactory.service.SimulationService;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.CubicCurve2D;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;

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
                drawSmoothGaussianCurve(g);
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
        }, 0, 100); // Update every 100 ms for smoother real-time effect
    }

    // Restart the simulation with new parameters
    private void restartSimulation() {
        simulationService.startSimulation(ballsSlider.getValue(), levelsSlider.getValue());
    }

    // Update the graph in the panel
    private void updateGraph() {
        simulationService.advanceSimulation(); // Make the balls fall one step at a time
        graphPanel.repaint(); // Repaint the graph panel to reflect the changes
    }

    // Method to draw the smooth Gaussian curve using spline interpolation
    private void drawSmoothGaussianCurve(Graphics g) {
        Map<Integer, Integer> bins = simulationService.getCurrentBins();

        int panelWidth = graphPanel.getWidth();
        int panelHeight = graphPanel.getHeight();
        int binWidth = panelWidth / bins.size();

        // Draw X and Y axes
        g.setColor(Color.BLACK);
        g.drawLine(50, panelHeight - 50, panelWidth - 50, panelHeight - 50); // X axis
        g.drawLine(50, panelHeight - 50, 50, 50); // Y axis

        // Find the maximum count to scale the bars correctly
        int maxCount = bins.values().stream().max(Integer::compare).orElse(1);

        // Prepare the lists for interpolation
        List<Integer> xPoints = new ArrayList<>();
        List<Integer> yPoints = new ArrayList<>();

        int xPosition = 50;
        for (Map.Entry<Integer, Integer> entry : bins.entrySet()) {
            int count = entry.getValue();
            int barHeight = (int) ((double) count / maxCount * (panelHeight - 100));
            int yPosition = panelHeight - 50 - barHeight;

            // Collect the points for spline interpolation
            xPoints.add(xPosition);
            yPoints.add(yPosition);

            xPosition += binWidth;
        }

        // Draw the smooth curve using the points and spline interpolation
        g.setColor(Color.BLUE);
        drawSpline(g, xPoints, yPoints);

        // Labels for the axes
        g.setColor(Color.BLACK);
        g.drawString("Levels", panelWidth / 2, panelHeight - 20);
        g.drawString("Balls", 10, panelHeight / 2);
    }

    // Function to draw a smooth curve using spline interpolation
    private void drawSpline(Graphics g, List<Integer> xPoints, List<Integer> yPoints) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2));

        // Spline interpolation between points
        for (int i = 0; i < xPoints.size() - 1; i++) {
            int x1 = xPoints.get(i);
            int y1 = yPoints.get(i);
            int x2 = xPoints.get(i + 1);
            int y2 = yPoints.get(i + 1);

            // Calculate control points for smooth curves
            int ctrlX1 = (x1 + x2) / 2;
            int ctrlY1 = y1;
            int ctrlX2 = (x1 + x2) / 2;
            int ctrlY2 = y2;

            // Draw a cubic curve between points
            g2d.draw(new CubicCurve2D.Float(x1, y1, ctrlX1, ctrlY1, ctrlX2, ctrlY2, x2, y2));
        }
    }
}
