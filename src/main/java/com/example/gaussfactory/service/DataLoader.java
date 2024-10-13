package com.example.gaussfactory.service;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Clase para cargar los datos desde un archivo CSV.
 * @version 1.0, 16/06/2021
 * @autor Pedro Alonso Tapia Lobo
 * @autor Patrik Paul Sirbu
 */
@Component
public class DataLoader {

    // Método para cargar los datos desde un archivo CSV con una ruta absoluta
    public List<Double> loadData(String filepath) throws IOException {
        List<Double> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            boolean firstLine = true; // Para saltar la cabecera

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // Ignorar la cabecera
                    continue;
                }


                String[] values = line.split(",");
                if (values.length > 1) {
                    try {
                        // Convertir el valor de la columna Profit a Double y agregarlo a la lista
                        double profit = Double.parseDouble(values[1].trim().replace("'", ""));
                        data.add(profit);
                    } catch (NumberFormatException e) {
                        System.err.println("No se pudo convertir a número: " + line);
                    }
                }
            }
        }
        return data;
    }
}

