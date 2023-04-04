package com.example.trackpals.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Document(collection = "usuarios")
public class Usuario {

    @Id
    private String id;
    //UniqueKey, si se intenta guardar un usuario con el mismo nombre devolvera un codigo 500
    //Lanza una DuplicateKeyException (o un MongoWriteException??)
    @Indexed(unique = true)
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    @Indexed(unique = true)
    @NotBlank(message = "El email es obligatorio")
    private String email;
    @NotBlank(message = "La contraseña es obligatoria")
    private String contrasenia;
    private String telefono;
    private Date fechaNac;
    private String direccion;
    private String descripcion;
    private Binary foto;
    private List<String> idsAmigos = new ArrayList<>();
    @DocumentReference
    private List<Excursion> excursionesApuntado = new ArrayList<>();

}
