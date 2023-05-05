package com.example.trackpals.service;

import com.example.trackpals.exception.ResourceNotFoundException;
import com.example.trackpals.model.Ruta;
import com.example.trackpals.repository.RutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RutaService {

    @Autowired
    RutaRepository rutaRepository;

    public List<Ruta> getAllRutas(){
        return rutaRepository.findAll();
    }

    public Ruta getRutaById(String id) throws ResourceNotFoundException {
        return rutaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ruta"));
    }

    public void createRuta(Ruta ruta){
        rutaRepository.save(ruta);
    }

}
