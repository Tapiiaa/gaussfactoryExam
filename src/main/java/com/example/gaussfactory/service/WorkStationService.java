package com.example.gaussfactory.service;

import com.example.gaussfactory.model.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Semaphore;

@Service
public class WorkStationService {
    private final Semaphore semaphore; //Inicializamos el semaforo

    @Autowired
    private int maxComponents;

    public WorkStationService() {
        this.semaphore = new Semaphore(maxComponents);
    }

    //Creamos un componente y lo producimos para luego poder liberar el semaforo
    public void produceComponent(Component component) throws InterruptedException {
        semaphore.acquire();
        System.out.println("Produciendo componente: " + component.getName());
        Thread.sleep(800);
        System.out.println("Componente producido: " + component.getName());
        semaphore.release();
    }
}
