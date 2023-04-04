package com.example.trackpals.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Date;

@Data
@Document(collection = "mensajes")
public class Mensaje {

    @Id
    private String id;
    private Date fecha;
    @NotBlank(message = "Escribe el mensaje")
    private String texto;
    @DocumentReference
    private Usuario autor;

}
