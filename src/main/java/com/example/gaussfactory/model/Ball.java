package com.example.gaussfactory.model;

public class Ball {
    private int position;
    private int currentLevel;

    public Ball() {
        this.position = 0;
        this.currentLevel = 0;
    }

    public void moveLeft() {
        position--;
    }

    public void moveRight() {
        position++;
    }

    public int getPosition() {
        return position;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void incrementLevel() {
        currentLevel++;
    }
}
