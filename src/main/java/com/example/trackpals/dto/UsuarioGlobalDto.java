package com.example.trackpals.dto;

import com.example.trackpals.model.Usuario;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class UsuarioGlobalDto {

    private String id;
    private String nombre;
    private String foto;
    private List<String> idsAmigos;
    private List<String> idsExcursionesApuntado;

    public UsuarioGlobalDto(Usuario usuario){
        this.id = usuario.getId();
        this.nombre = usuario.getNombre();
        this.foto = usuario.getFoto();
        this.idsAmigos = usuario.getIdsAmigos();
        this.idsExcursionesApuntado = usuario.getIdsExcursionesApuntado();
    }

}
