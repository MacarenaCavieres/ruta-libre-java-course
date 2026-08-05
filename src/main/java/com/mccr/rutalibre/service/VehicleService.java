package com.mccr.rutalibre.service;

import com.mccr.rutalibre.model.Vehicle;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {
    private List<Vehicle> carsList = new ArrayList<>();

    @PostConstruct
    public void loadData() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Vehicle>> typeReference = new TypeReference<List<Vehicle>>() {
            };
            InputStream inputStream = TypeReference.class.getResourceAsStream("/vehicles.json");

            if (inputStream == null) {
                System.out.println("Error: No se encontró el archivo autos.json en src/main/resources/");
                return;
            }

            this.carsList = mapper.readValue(inputStream, typeReference);
            System.out.println("¡Base de datos de autos cargada con éxito! Total: " + this.carsList.size());
        } catch (Exception e) {
            System.out.println("Error al procesar el archivo JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Vehicle> getAll() {
        return this.carsList;
    }
}
