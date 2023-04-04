package com.example.trackpals.service;

import com.example.trackpals.exception.AttributeException;
import com.example.trackpals.exception.ResourceNotFoundException;
import com.example.trackpals.model.Usuario;
import com.example.trackpals.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Usuario>getAllUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(String id) throws ResourceNotFoundException {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("usuario"));
    }

    public List<Usuario> searchUsuariosByName(String nombre){
        return usuarioRepository.searchUsuarios(nombre);
    }

    public Usuario createUsuario(Usuario usuario) throws AttributeException {
        //Comprobar que el nombre y el email no existen
        boolean existeNombre = usuarioRepository.existsByNombre(usuario.getNombre());
        boolean existeEmail = usuarioRepository.existsByEmail(usuario.getEmail());
        if (existeNombre || existeEmail){
            String message = "Ese ";
            message += (existeNombre) ? "nombre" : "";
            message += (message.length() != 4) ? " e " : "";
            message += (existeEmail) ? "email" : "";
            throw new AttributeException(message);
        }
        //Guardar usuario
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(String id, Usuario usuario) throws ResourceNotFoundException {
        Usuario foundUsuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("usuario"));
        foundUsuario.setContrasenia(usuario.getContrasenia());
        foundUsuario.setTelefono(usuario.getTelefono());
        foundUsuario.setFechaNac(usuario.getFechaNac());
        foundUsuario.setDireccion(usuario.getDireccion());
        foundUsuario.setDescripcion(usuario.getDescripcion());
        foundUsuario.setFoto(usuario.getFoto());
        return usuarioRepository.save(foundUsuario);
    }

    public void deleteUsuario(String id) throws ResourceNotFoundException {
        Usuario foundUsuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("usuario"));
        usuarioRepository.deleteById(id);
    }

}
