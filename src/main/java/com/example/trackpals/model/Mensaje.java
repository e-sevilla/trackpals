package com.example.trackpals.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
@Document(collection = "mensajes")
public class Mensaje {

    @Id
    private String id;
    private Long fecha = new Date().getTime(); //fecha en milisegundos
    @NotBlank(message = "Escribe el mensaje")
    private String texto;
    @DocumentReference
    private Usuario autor;

}
