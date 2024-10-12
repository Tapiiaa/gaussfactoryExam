package com.example.gaussfactory.controller;

//import com.example.gaussfactory.factory.ComponentFactory;
import com.example.gaussfactory.factory.ComponentFactory;
import com.example.gaussfactory.model.Component;
import com.example.gaussfactory.service.ComponentAssemblerService;
import com.example.gaussfactory.service.WorkStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Clase controlador de la produccion de componentes.
 * @version 1.0, 16/06/2021
 * @autor Pedro Alonso Tapia Lobo
 * @autor Patrik Paul Sirbu
 */
@Controller
public class ProductionController {
    private final WorkStationService workStationService;
    private final ComponentAssemblerService assemblerService;

    @Autowired //Inyectamos las dependencias necesarias para el controlador con el auto-wired.
    /**
     * Constructor de la clase ProductionController.
     * @param workStationService Servicio de la estacion de trabajo.
     * @param assemblerService Servicio del ensamblador de componentes.
     */
    public ProductionController(WorkStationService workStationService, ComponentAssemblerService assemblerService) {
        this.workStationService = workStationService;
        this.assemblerService = assemblerService;
    }

    /**
     * Metodo con el cual iniciamos la produccion de un componente usando los servicios de WorkStationService y ComponentAssemblerService.
     * @throws InterruptedException Excepcion lanzada en caso de que el hilo sea interrumpido.
     */

    public void startProduction() throws InterruptedException{
        Component ball = ComponentFactory.createComponent("ball");
        workStationService.produceComponent(ball);
        assemblerService.assembleComponent();
    }
}
