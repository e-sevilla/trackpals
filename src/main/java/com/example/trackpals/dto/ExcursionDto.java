package com.example.trackpals.dto;

import com.example.trackpals.model.Excursion;
import com.example.trackpals.model.Usuario;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ExcursionDto {

    private String id;
    private String nombre;
    private Long fecha; //fecha en milisegundos
    private String puntoEncuentro;
    private Boolean privada;
    private String foto; //foto en base64
    private Usuario creador;

    public ExcursionDto(Excursion excursion){
        this.id = excursion.getId();
        this.nombre = excursion.getNombre();
        this.fecha = excursion.getFecha();
        this.puntoEncuentro = excursion.getPuntoEncuentro();
        this.privada = excursion.getPrivada();
        this.foto = excursion.getFoto();
        this.creador = excursion.getCreador();
    }

}
