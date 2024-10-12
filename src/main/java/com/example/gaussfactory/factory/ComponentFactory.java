package com.example.gaussfactory.factory;

import com.example.gaussfactory.model.Ball;
import com.example.gaussfactory.model.Component;

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
