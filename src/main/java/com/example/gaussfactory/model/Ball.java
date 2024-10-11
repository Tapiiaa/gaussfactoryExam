package com.example.gaussfactory.model;

public class Ball {
    private int position;

    public Ball() {
        this.position = 0;
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
}

