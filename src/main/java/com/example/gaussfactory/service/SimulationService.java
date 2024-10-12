package com.example.gaussfactory.service;
import java.util.ArrayList;
import java.util.List;
import com.example.gaussfactory.model.Ball;
import org.springframework.stereotype.Service;
/**
 * Clase para simular la caída de bolas.
 * @version 1.0, 16/06/2021
 * @autor Pedro Alonso Tapia Lobo
 * @autor Patrik Paul Sirbu
 */
@Service
public class SimulationService {

    private List<Double> data;
    private List<Ball> balls; //Creamos una lista de bolas para simular la caída de varias bolas.
    private int speed = 1; //Velocidad de la simulación por defecto.

    public SimulationService() {
        this.balls = new ArrayList<>();
    }

    // Método para obtener todos los datos de la simulación
    public List<Double> getAllData() {
        return this.data;
    }

    public List<Ball> getBalls(){
        return this.balls;
    }

    public void initializeBalls(int numberOfBalls){
        // Inicializar las bolas con la cantidad especificada
        balls.clear();
        for (int i = 0; i < numberOfBalls; i++) {
            balls.add(new Ball());
        }
    }

    public void setSimulationData(List<Double> data){
        this.data = data;
    }

    public void startSimulation(){
        // Iniciar la simulación de la caída de las bolas
        for (Ball ball : balls){
            for (int i = 0; i < data.size(); i++) {
                ball.fall();
                try{
                    Thread.sleep(1000 / speed);  //Controla la velocidad de la simulacion.
                } catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void resetSimulation(){
        balls.clear();
    }

    public void stopSimulation(){
        balls = null;  //Eliminamos el objeto Ball para detener la simulación
    }

    public void setSimulationSpeed(int speed){
        this.speed = speed;  //Establecemos la velocidad de la simulación
    }

    public List<Double> getBallPositions(){
        List<Double> positions = new ArrayList<>();
        for (Ball ball : balls){
            positions.add((double) ball.getPosition());
        }
        return positions;
    }
}

