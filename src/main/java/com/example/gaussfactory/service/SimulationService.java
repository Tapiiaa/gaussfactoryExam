package com.example.gaussfactory.service;
import java.util.ArrayList;
import java.util.List;
import com.example.gaussfactory.model.Ball;

public class SimulationService {

    private List<Double> data;
    private List<Ball> balls; //Creamos una lista de bolas para simular la caída de varias bolas.
    private int speed = 1; //Velocidad de la simulación por defecto.

    // Constructor que recibe los datos de la simulación
    public SimulationService(List<Double> data) {
        this.data = data;
        this.balls = new ArrayList<>();
    }

    // Método para obtener todos los datos de la simulación
    public List<Double> getAllData() {
        return this.data;
    }

    public void initializeBalls(int numberOfBalls){
        // Inicializar las bolas con la cantidad especificada
        for (int i = 0; i < numberOfBalls; i++) {
            balls.add(new Ball());
        }
    }

    public void startSimulation(){
        // Iniciar la simulación de la caída de las bolas
        for (int i = 0; i < data.size(); i++) {
            ball.fall();
        }
    }

    public void resetSimulation(){
        ball = new Ball();  //Creamos un nuevo objeto Ball para reiniciar la simulación
    }

    public void stopSimulation(){
        ball = null;  //Eliminamos el objeto Ball para detener la simulación
    }

    public void setSimulationSpeed(int speed){
        ball.setSpeed(speed);  //Establecemos la velocidad de la simulación
    }
}

