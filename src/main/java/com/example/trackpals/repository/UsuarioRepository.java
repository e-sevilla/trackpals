package com.example.trackpals.repository;

import com.example.trackpals.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    public boolean existsByNombre(String nombre);
    public boolean existsByEmail(String email);

    // Buscar el usuario cuyo nombre coincida exactamente con el buscado
    @Query("{'nombre': ?0}")
    public Usuario findByNombre(String nombre);

    // Buscar todos los usuarios cuyo nombre contenga la cadena a buscar
    @Query("{'nombre': {$regex: ?0}}")
    public List<Usuario> searchUsuarios(String nombre);

}
