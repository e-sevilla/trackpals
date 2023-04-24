package com.example.trackpals.model;

import com.example.trackpals.dto.UsuarioRegistroDto;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
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
    private Long fechaNac; //fecha en milisegundos
    private String direccion;
    private String descripcion;
    private String foto; //foto en base64
    private List<String> idsAmigos = new ArrayList<>();
    private List<String> idsExcursionesApuntado = new ArrayList<>();

    public Usuario(UsuarioRegistroDto usuarioRegistroDto){
        this.nombre = usuarioRegistroDto.getNombre();
        this.email = usuarioRegistroDto.getEmail();
        this.contrasenia = usuarioRegistroDto.getContrasenia();
    }

}
