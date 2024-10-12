package com.example.gaussfactory.controller;

//import com.example.gaussfactory.factory.ComponentFactory;
import com.example.gaussfactory.factory.ComponentFactory;
import com.example.gaussfactory.model.Component;
import com.example.gaussfactory.service.ComponentAssemblerService;
import com.example.gaussfactory.service.WorkStationService;
import com.example.gaussfactory.synchronization.SyncManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ProductionController {
    private final WorkStationService workStationService;
    private final ComponentAssemblerService assemblerService;

    @Autowired //Inyectamos las dependencias necesarias para el controlador con el auto-wired.
    public ProductionController(WorkStationService workStationService, ComponentAssemblerService assemblerService) {
        this.workStationService = workStationService;
        this.assemblerService = assemblerService;
    }

    //Metodo con el cual iniciamos la produccion de un componente usando los servicios de WorkStationService y ComponentAssemblerService.
    public void startProduction() throws InterruptedException{
        Component ball = ComponentFactory.createComponent("ball");
        workStationService.produceComponent(ball);
        assemblerService.assembleComponent();
    }
}
