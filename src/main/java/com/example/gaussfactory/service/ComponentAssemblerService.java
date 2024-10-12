package com.example.gaussfactory.service;

import com.example.gaussfactory.model.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
/**
 * Clase que ensambla los componentes.
 * @version 1.0, 16/06/2021
 * @autor Pedro Alonso Tapia Lobo
 * @autor Patrik Paul Sirbu
 */
@Service
public class ComponentAssemblerService {

    //Usamos un BlockingQueue para poder ensamblar los componentes de forma segura
    private final BlockingQueue<Component> queue;

    public ComponentAssemblerService(BlockingQueue<Component> queue) {
        this.queue = queue;
    }

    /**
     * Metodo que ensambla un componente.
     * @throws InterruptedException
     */
    public void assembleComponent() throws InterruptedException {
        Component component = queue.take();
        System.out.println("Componente ensamblado: " + component.getName());
    }
}
