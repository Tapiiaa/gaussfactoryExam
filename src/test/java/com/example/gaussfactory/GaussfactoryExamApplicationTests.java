package com.example.gaussfactory;

import com.example.gaussfactory.controller.SimulationController;
import com.example.gaussfactory.gaussfactory.GaussfactoryExamApplication;
import com.example.gaussfactory.gaussfactory.MainApp;
import com.example.gaussfactory.service.DataLoader;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = GaussfactoryExamApplication.class)
class GaussfactoryExamApplicationTests {

    @Test
    void contextLoads() {
    } // Con este test se verifica que Spring se inicie correctamente.



}
