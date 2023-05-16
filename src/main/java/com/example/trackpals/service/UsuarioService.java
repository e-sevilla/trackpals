package com.example.trackpals.service;

import com.example.trackpals.exception.AttributeException;
import com.example.trackpals.exception.ResourceNotFoundException;
import com.example.trackpals.model.Usuario;
import com.example.trackpals.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public String encriptarContrasenia(String contrasenia) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] contraseniaCifrada = md.digest(contrasenia.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : contraseniaCifrada) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }

    public List<Usuario>getAllUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(String id) throws ResourceNotFoundException {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario"));
    }

    //Buscar el usuario cuyo nombre coincida exactamente con el buscado
    public Usuario getUsuarioByName(String nombre) throws ResourceNotFoundException {
        return usuarioRepository.findByNombre(nombre)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario"));
    }

    //Buscar usuarios cuyo nombre contenga la cadena a buscar
    public List<Usuario> searchUsuariosByName(String nombre){
        return usuarioRepository.searchUsuarios(nombre);
    }

    //Buscar los usuarios de una lista de ids
    public List<Usuario> searchFriends(String[] ids) { return usuarioRepository.findAllFriends(ids); }

    public Usuario createUsuario(Usuario usuario) throws AttributeException {
        //Comprobar que el nombre y el email no existen
        boolean existeNombre = usuarioRepository.existsByNombre(usuario.getNombre());
        boolean existeEmail = usuarioRepository.existsByEmail(usuario.getEmail());
        if (existeNombre || existeEmail){
            String message = "Ese ";
            message += (existeNombre) ? "nombre" : "";
            message += (existeNombre && existeEmail) ? " e " : "";
            message += (existeEmail) ? "email" : "";
            throw new AttributeException(message);
        }
        //Guardar usuario
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(String id, Usuario usuario) throws ResourceNotFoundException {
        Usuario foundUsuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario"));
        foundUsuario.setContrasenia(usuario.getContrasenia());
        foundUsuario.setFechaNac(usuario.getFechaNac());
        foundUsuario.setDireccion(usuario.getDireccion());
        foundUsuario.setDescripcion(usuario.getDescripcion());
        foundUsuario.setFoto(usuario.getFoto());
        foundUsuario.setIdsAmigos(usuario.getIdsAmigos());
        foundUsuario.setIdsExcursionesApuntado(usuario.getIdsExcursionesApuntado());
        return usuarioRepository.save(foundUsuario);
    }

    public void deleteUsuario(String id) throws ResourceNotFoundException {
        Usuario foundUsuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario"));
        usuarioRepository.deleteById(id);
    }

}
