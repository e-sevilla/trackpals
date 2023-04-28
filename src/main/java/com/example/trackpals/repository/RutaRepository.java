package com.example.trackpals.repository;

import com.example.trackpals.model.Ruta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RutaRepository extends MongoRepository<Ruta, String> {



}
