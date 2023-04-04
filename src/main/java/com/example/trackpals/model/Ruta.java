package com.example.trackpals.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "rutas")
public class Ruta {

    @Id
    private String id;
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    @NotBlank(message = "El gpx es obligatorio")
    private String gpx;

}
