package com.example.trackpals.service;

import com.example.trackpals.model.Mensaje;
import com.example.trackpals.repository.MensajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MensajeService {

    @Autowired
    MensajeRepository mensajeRepository;

    public void createMensaje(Mensaje mensaje){
        mensajeRepository.save(mensaje);
    }

    public void deleteMensaje(Mensaje mensaje){
        mensajeRepository.delete(mensaje);
    }

}
