package com.example.trackpals.repository;

import com.example.trackpals.model.Excursion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExcursionRepository extends MongoRepository<Excursion, String> {

    // Buscar excursiones futuras
    @Query(value = "{'fecha': {$gte : ?0}}", fields = "{'ruta': 0, 'mensajes': 0, 'descripcion': 0}")
    List<Excursion> findAllActive(Long fecha);

    // Buscar excursiones de una lista de ids
    @Query(value = "{'id': {$in : ?0}}", fields = "{'ruta': 0, 'mensajes': 0, 'descripcion': 0}")
    List<Excursion> findAllMine(String[] ids);

}
