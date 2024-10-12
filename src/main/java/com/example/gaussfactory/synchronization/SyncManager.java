package com.example.gaussfactory.synchronization;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * Clase que representa un administrador de sincronización.
 * @version 1.0, 16/06/2021
 * @autor Pedro Alonso Tapia Lobo
 * @autor Patrik Paul Sirbu
 */
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
