package com.example.trackpals.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Document(collection = "excursiones")
public class Excursion {

    @Id
    private String id;
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    @NotBlank(message = "La fecha es obligatoria")
    private Date fecha;
    @NotBlank(message = "El lugar es obligatorio")
    private String puntoEncuentro;
    private Boolean privada = false;
    private String descripcion;
    private Binary foto;
    @DocumentReference
    private Usuario creador;
    @DocumentReference
    private Ruta ruta;
    @DocumentReference
    private List<Mensaje> mensajes = new ArrayList<>();

}
