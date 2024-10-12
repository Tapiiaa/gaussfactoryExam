package com.example.gaussfactory.factory;

import com.example.gaussfactory.model.Ball;
import com.example.gaussfactory.model.Component;
import com.example.gaussfactory.synchronization.SyncManager;

public class ComponentFactory {

    public static Component createComponent(String type) {
        switch(type){
            case "ball":
              return new Ball();
            default:
               throw new IllegalArgumentException("El tipo de componente no es valido");
        }
    }
}
