package com.example.trackpals.repository;

import com.example.trackpals.model.Ruta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RutaRepository extends MongoRepository<Ruta, String> {

    @Query(value = "{}", fields = "{'gpx': 0}")
    List<Ruta> findAll();

}
