package com.example.trackpals.repository;

import com.example.trackpals.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    // Buscar el usuario cuyo nombre coincida exactamente con el buscado
    @Query("{'username': ?0}")
    public Usuario findByUsername(String username);

    // Buscar todos los usuarios cuyo nombre contenga la cadena a buscar
    @Query("{'username': {$regex: ?0}}")
    public List<Usuario> searchUsuarios(String username);

}
