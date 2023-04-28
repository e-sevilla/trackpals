package com.example.trackpals.repository;

import com.example.trackpals.model.Excursion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcursionRepository extends MongoRepository<Excursion, String> {



}
