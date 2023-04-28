package com.example.trackpals.service;

import com.example.trackpals.exception.ResourceNotFoundException;
import com.example.trackpals.model.Excursion;
import com.example.trackpals.repository.ExcursionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExcursionService {

    @Autowired
    ExcursionRepository excursionRepository;

    public List<Excursion> getAllExcursiones(){
        return excursionRepository.findAll();
    }

    public Excursion getExcursionById(String id) throws ResourceNotFoundException {
        return excursionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("excursión"));
    }

    public Excursion createExcursion(Excursion excursion){
        return excursionRepository.save(excursion);
    }

    public Excursion updateExcursion(String id, Excursion excursion) throws ResourceNotFoundException {
        Excursion foundExcursion = excursionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("excursión"));
        foundExcursion.setNombre(excursion.getNombre());
        foundExcursion.setFecha(excursion.getFecha());
        foundExcursion.setPuntoEncuentro(excursion.getPuntoEncuentro());
        foundExcursion.setPrivada(excursion.getPrivada());
        foundExcursion.setDescripcion(excursion.getDescripcion());
        foundExcursion.setFoto(excursion.getFoto());
        foundExcursion.setRuta(excursion.getRuta());
        foundExcursion.setMensajes(excursion.getMensajes());
        return excursionRepository.save(foundExcursion);
    }

    public void deleteExcursion(String id) throws ResourceNotFoundException {
        Excursion foundExcursion = excursionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("excursión"));
        excursionRepository.deleteById(id);
    }

}
