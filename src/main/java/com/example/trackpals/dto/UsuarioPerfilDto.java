package com.example.trackpals.dto;

import com.example.trackpals.model.Usuario;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class UsuarioPerfilDto {

    private String id;
    private String nombre;
    private Long fechaNac;
    private String direccion;
    private String descripcion;
    private String foto;

    public UsuarioPerfilDto(Usuario usuario){
        this.id = usuario.getId();
        this.nombre = usuario.getNombre();
        this.fechaNac = usuario.getFechaNac();
        this.direccion = usuario.getDireccion();
        this.descripcion = usuario.getDescripcion();
        this.foto = usuario.getFoto();
    }

}
