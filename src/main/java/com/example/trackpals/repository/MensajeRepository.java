package com.example.trackpals.repository;

import com.example.trackpals.model.Mensaje;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensajeRepository extends MongoRepository<Mensaje, String> {



}
