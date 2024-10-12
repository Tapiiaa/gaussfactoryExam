package com.example.gaussfactory.model;

import java.util.Random;

public class Ball extends Component{
    private int position;
    private int currentLevel;
    private Random random;

    public Ball() {
        super("Bola de Gauss");
        this.position = 0;
        this.currentLevel = 0;
        this.random = new Random();
    }

    public Ball(int position) {
        super("Bola de Gauss");
        this.position = position;
        this.currentLevel = 0;
        this.random = new Random();
    }

    public void moveLeft() {
        position--;
    }

    public void moveRight() {
        position++;
    }

    //Genera un movimiento aleatorio.
    public void moveRandom(){
        double gaussianValue = random.nextGaussian();
        if(gaussianValue > 0){
            moveRight();
        } else {
            moveLeft();
        }
    }

    //Simulamos la caida a traves de un nivel, dejando que la bola se mueva aleatoriamente.
    public void fall(){
        moveRandom();
        incrementLevel();
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

    public void setSpeed(int speed){
        random.setSeed(speed);
    }
}
