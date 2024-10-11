package com.example.gaussfactory.service;

import com.example.gaussfactory.model.Ball;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SimulationService {

    private final List<Ball> balls = Collections.synchronizedList(new ArrayList<>());
    private final Map<Integer, Integer> bins = new ConcurrentHashMap<>();

    private final Random random = new Random();

    private int numberOfLevels;
    private int currentLevel = 0;
    private boolean simulationRunning = false;

    public void startSimulation(int numberOfBalls, int numberOfLevels) {
        this.numberOfLevels = numberOfLevels;
        this.currentLevel = 0;
        this.simulationRunning = true;

        balls.clear();
        bins.clear();

        for (int i = 0; i < numberOfBalls; i++) {
            Ball ball = new Ball();
            balls.add(ball);
        }
    }

    public boolean advanceOneLevel() {
        if (!simulationRunning || currentLevel >= numberOfLevels) {
            simulationRunning = false;
            return false;
        }

        synchronized (balls) {
            for (Ball ball : balls) {
                if (ball.getCurrentLevel() <= currentLevel) {
                    if (random.nextBoolean()) {
                        ball.moveRight();
                    } else {
                        ball.moveLeft();
                    }
                    ball.incrementLevel();
                }
            }

            // Actualizar los contenedores (bins)
            bins.clear();
            for (Ball ball : balls) {
                bins.merge(ball.getPosition(), 1, Integer::sum);
            }
        }

        currentLevel++;
        return true;
    }

    public Map<Integer, Integer> getBins() {
        return bins;
    }

    public boolean isSimulationRunning() {
        return simulationRunning;
    }
}
