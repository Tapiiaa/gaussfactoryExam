package com.example.gaussfactory.service;

import com.example.gaussfactory.model.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;

@Service
public class ComponentAssemblerService {

    //Usamos un BlockingQueue para poder ensamblar los componentes de forma segura
    private final BlockingQueue<Component> queue;

    public ComponentAssemblerService(BlockingQueue<Component> queue) {
        this.queue = queue;
    }

    //Metodo para ensamblar un componente que podra lanzar una excepcion si no se puede ensamblar
    public void assembleComponent() throws InterruptedException {
        Component component = queue.take();
        System.out.println("Componente ensamblado: " + component.getName());
    }
}
