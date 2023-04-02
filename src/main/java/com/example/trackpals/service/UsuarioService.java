package com.example.trackpals.service;

import com.example.trackpals.model.Usuario;
import com.example.trackpals.repository.UsuarioRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Usuario>getAllUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario getUsuario(String username){
        return usuarioRepository.findByUsername(username);
    }

    public List<Usuario> searchUsuarios(String username){
        return usuarioRepository.searchUsuarios(username);
    }

    public Usuario createUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(String id, Usuario usuario){
        Usuario foundUsuario = usuarioRepository
                .findById(id)
                .orElseThrow(RuntimeException::new);
        foundUsuario.setUsername(usuario.getUsername());
        foundUsuario.setContrasenia(usuario.getContrasenia());
        return usuarioRepository.save(foundUsuario);
    }

    public void deleteUsuario(String id){
//        Usuario foundUsuario = usuarioRepository
//                .findById(id)
//                .orElseThrow(RuntimeException::new);
        usuarioRepository.deleteById(id);
    }

}
