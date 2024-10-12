package com.example.gaussfactory.synchronization;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SyncManager {
    //Creamos un lock para poder sincronizar el acceso a la seccion critica.
    private final Lock lock = new ReentrantLock();

    public void performSyncTask(Runnable task){
        lock.lock();
        try {
            task.run(); //Ejecutamos la tarea
        } finally {
            lock.unlock();  //Liberamos el lock para que otros hilos puedan acceder.
        }
    }

}
