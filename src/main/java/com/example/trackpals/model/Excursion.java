package com.example.trackpals.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
@Document(collection = "excursiones")
public class Excursion {

    @Id
    private String id;
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    @NotNull(message = "La fecha es obligatoria")
    private Long fecha; //fecha en milisegundos
    @NotBlank(message = "El lugar es obligatorio")
    private String puntoEncuentro;
    private Boolean privada = false;
    private String descripcion;
    private String foto; //foto en base64
    @DocumentReference
    @NotNull(message = "El usuario creador es obligatorio")
    private Usuario creador;
    @DocumentReference
    private Ruta ruta;
    @DocumentReference
    private List<Mensaje> mensajes = new ArrayList<>();

}
