package com.example.gaussfactory;

import java.io.IOException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        DataLoader loader = new DataLoader();
        try {
            // Utiliza una ruta absoluta como prueba
            String absolutePath = "C:/Users/Usuario/gaussfactoryExam/src/main/resources/CompanyABCProfit.csv"; // Cambia esto a la ruta absoluta de tu archivo

            // Cargar los datos desde el archivo CSV
            List<Double> data = loader.loadData(absolutePath);

            // Imprimir los datos cargados para verificar
            System.out.println("Datos cargados: " + data);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
