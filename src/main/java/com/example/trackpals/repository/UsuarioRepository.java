package com.example.trackpals.repository;

import com.example.trackpals.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    boolean existsByNombre(String nombre);
    boolean existsByEmail(String email);

    // Buscar el usuario cuyo nombre coincida exactamente con el buscado
    @Query("{'nombre': ?0}")
    Optional<Usuario> findByNombre(String nombre);

    // Buscar todos los usuarios cuyo nombre contenga la cadena a buscar (case insensitive)
    @Query("{'nombre': {$regex: ?0, $options: 'i'}}")
    List<Usuario> searchUsuarios(String nombre);

}
