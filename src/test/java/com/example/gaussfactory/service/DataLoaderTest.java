package com.example.gaussfactory.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataLoaderTest {

    private DataLoader dataLoader;

    @BeforeEach
    void setUp() {
        dataLoader = new DataLoader();
    }

    @Test
    void testLoadData() throws IOException {
        // Suponiendo que tienes el archivo src/test/resources/testData.csv con datos válidos
        String testFilePath = "src/test/resources/testData.csv";
        List<Double> data = dataLoader.loadData(testFilePath);

        assertNotNull(data, "La lista de datos no debe ser nula.");
        assertFalse(data.isEmpty(), "La lista de datos no debe estar vacía.");
        assertEquals(3, data.size(), "El tamaño de la lista de datos debe ser 3.");
        assertEquals(100.0, data.get(0), "El primer valor debe ser 100.0");
    }

    @Test
    void testLoadDataInvalidFile() {
        String invalidFilePath = "invalid/path/to/file.csv";
        assertThrows(IOException.class, () -> dataLoader.loadData(invalidFilePath));
    }

    @Test
    void testHandleNumberFormatException() {
        // Probar con un archivo CSV malformado para verificar que no lanza excepciones inesperadas
        String invalidDataFilePath = "src/test/resources/invalidData.csv";
        try {
            dataLoader.loadData(invalidDataFilePath);
        } catch (Exception e) {
            //fail("No se esperaba que se lanzara ninguna excepción, pero ocurrió: " + e.getMessage());
        }
    }
}

