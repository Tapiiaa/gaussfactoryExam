package com.example.gaussfactory.model;
public class Ball {

    private int currentPosition;
    private final int totalLevels;

    public Ball(int totalLevels) {
        this.currentPosition = 0;
        this.totalLevels = totalLevels;
    }

    // Simular el movimiento de la bola por un nivel
    public void move() {
        currentPosition += Math.random() < 0.5 ? -1 : 1;
        currentPosition = Math.max(0, Math.min(totalLevels, currentPosition));
    }

    // Obtener la posición actual de la bola
    public int getCurrentPosition() {
        return currentPosition;
    }
}
