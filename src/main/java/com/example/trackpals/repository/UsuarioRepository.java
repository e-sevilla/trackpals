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
    @Query(value = "{'nombre': {$regex: ?0, $options: 'i'}}", fields = "{'id': 1, 'nombre': 1, 'foto': 1}")
    List<Usuario> searchUsuarios(String nombre);

    // Buscar usuarios de una lista de ids
    @Query(value = "{'id': {$in : ?0}}", fields = "{'id': 1, 'nombre': 1, 'foto': 1}")
    List<Usuario> findAllFriends(String[] ids);

}
