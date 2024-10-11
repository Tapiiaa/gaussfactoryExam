package com.example.gaussfactory.service;import java.util.HashMap;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimulationService {

    private int numberOfBalls;
    private int numberOfLevels;
    private Map<Integer, Integer> bins;
    private boolean simulationRunning;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public SimulationService() {
        bins = new HashMap<>();
    }

    public void startSimulation(int numberOfBalls, int numberOfLevels) {
        lock.lock();
        try {
            this.numberOfBalls = numberOfBalls;
            this.numberOfLevels = numberOfLevels;
            this.simulationRunning = true;
            bins.clear();
            for (int i = 0; i <= numberOfLevels; i++) {
                bins.put(i, 0);
            }
        } finally {
            lock.unlock();
        }
    }

    public boolean advanceSimulation() {
        lock.lock();
        try {
            if (!simulationRunning || numberOfBalls <= 0) {
                simulationRunning = false;
                return false;
            }

            int position = 0;
            for (int i = 0; i < numberOfLevels; i++) {
                position += Math.random() < 0.5 ? -1 : 1;
                position = Math.max(0, Math.min(numberOfLevels, position));
            }

            bins.put(position, bins.get(position) + 1);
            numberOfBalls--;

            condition.signalAll();
            return true;
        } finally {
            lock.unlock();
        }
    }

    public Map<Integer, Integer> getCurrentBins() {
        lock.lock();
        try {
            return new HashMap<>(bins);
        } finally {
            lock.unlock();
        }
    }
}
