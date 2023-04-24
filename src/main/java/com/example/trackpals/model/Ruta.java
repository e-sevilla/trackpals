package com.example.trackpals.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
@Document(collection = "rutas")
public class Ruta {

    @Id
    private String id;
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    @NotBlank(message = "El gpx es obligatorio")
    private String gpx; //contenido del archivo gpx en un string

}
