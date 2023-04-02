package com.example.trackpals.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "usuarios")
public class Usuario {

    @Id
    private String id;
    @Indexed(unique = true)
    private String username;
    //UniqueKey, si se intenta guardar un usuario con el mismo nombre devolvera un codigo 500
    //Lanza una DuplicateKeyException (o un MongoWriteException??)
    private String contrasenia;

}
