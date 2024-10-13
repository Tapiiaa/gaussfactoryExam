package com.example.gaussfactory.model;

import java.util.Random;
/**
 * Clase que representa una bola de Gauss.
 * @version 1.0, 16/06/2021
 * @autor Pedro Alonso Tapia Lobo
 * @autor Patrik Paul Sirbu
 */

public class Ball extends Component{
    private int position;
    private int currentLevel;
    private Random random;
    /**
     * Constructor de la clase.
     */
    public Ball() {
        super("Bola de Gauss");
        this.position = 0;
        this.currentLevel = 0;
        this.random = new Random();
    }
    /**
     * Constructor de la clase.
     * @param position Posicion inicial de la bola.
     */
    public Ball(int position) {
        super("Bola de Gauss");
        this.position = position;
        this.currentLevel = 0;
        this.random = new Random();
    }

    /**
     * Metodo que mueve la bola a la izquierda.
     */
    public void moveLeft() {
        position--;
    }

    /**
     * Metodo que mueve la bola a la derecha.
     */
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
