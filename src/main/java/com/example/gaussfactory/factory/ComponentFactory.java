package com.example.gaussfactory.factory;
/**
 * Clase que crea componentes.
 * @version 1.0, 16/06/2021
 * @autor Pedro Alonso Tapia Lobo
 * @autor Patrik Paul Sirbu
 */
import com.example.gaussfactory.model.Ball;
import com.example.gaussfactory.model.Component;
import com.example.gaussfactory.synchronization.SyncManager;

public class ComponentFactory {
    /**
     * Metodo que crea un componente.
     * @param type Tipo de componente a crear.
     * @return Componente creado.
     */
    public static Component createComponent(String type) {
        switch(type){
            case "ball":
              return new Ball();
            default:
               throw new IllegalArgumentException("El tipo de componente no es valido");
        }
    }
}
