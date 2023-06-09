package com.example.trackpals.service;

import com.example.trackpals.exception.ResourceNotFoundException;
import com.example.trackpals.model.Excursion;
import com.example.trackpals.repository.ExcursionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExcursionService {

    @Autowired
    ExcursionRepository excursionRepository;

    public List<Excursion> getAllActive() { return excursionRepository.findAllActive(new Date().getTime()); }

    public List<Excursion> getAllMine(String[] ids){ return excursionRepository.findAllMine(ids); }

    public Excursion getExcursionById(String id) throws ResourceNotFoundException {
        return excursionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Excursión"));
    }

    public Excursion createExcursion(Excursion excursion){
        return excursionRepository.save(excursion);
    }

    public Excursion updateExcursion(String id, Excursion excursion) throws ResourceNotFoundException {
        Excursion foundExcursion = excursionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Excursión"));
        foundExcursion.setNombre(excursion.getNombre());
        foundExcursion.setFecha(excursion.getFecha());
        foundExcursion.setLatitud(excursion.getLatitud());
        foundExcursion.setLongitud(excursion.getLongitud());
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
                .orElseThrow(() -> new ResourceNotFoundException("Excursión"));
        excursionRepository.deleteById(id);
    }

}
