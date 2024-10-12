package com.example.gaussfactory.service;

import com.example.gaussfactory.model.Component;
import com.example.gaussfactory.synchronization.SyncManager;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;

@Service
public class ComponentAssemblerService {

    //Usamos un BlockingQueue para poder ensamblar los componentes de forma segura
    private final BlockingQueue<Component> queue;
    private final SyncManager syncManager;

    public ComponentAssemblerService(BlockingQueue<Component> queue) {
        this.queue = queue;
        this.syncManager = new SyncManager();
    }

    //Metodo para ensamblar un componente que podra lanzar una excepcion si no se puede ensamblar
    public void assembleComponent() throws InterruptedException {
        syncManager.performSyncTask(() -> {
            try {
                Component component = queue.take();
                System.out.println("Componente ensamblado: " + component.getName());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        });
    }
}
