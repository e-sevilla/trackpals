package com.example.trackpals.controller;

import com.example.trackpals.model.Usuario;
import com.example.trackpals.service.UsuarioService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin //habilita CORS para que el cliente Angular (u otro??) pueda consultar la API sin errores
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("")
    public List<Usuario> listarUsuarios(){
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("{username}")
    public Usuario obtenerUsuario(@PathVariable String username){
        return usuarioService.getUsuario(username);
    }

    @GetMapping("buscar/{username}")
    public List<Usuario> buscarUsuarios(@PathVariable String username){
        return usuarioService.searchUsuarios(username);
    }

    @ResponseStatus(HttpStatus.CREATED) //codigo 201, indica que se ha creado algo
    @PostMapping("")
    public Usuario crearUsuario(@RequestBody Usuario usuario){
        return usuarioService.createUsuario(usuario);
    }

    @PutMapping("{id}")
    public Usuario actualizarUsuario(@PathVariable String id, @RequestBody Usuario usuario){
        return usuarioService.updateUsuario(id, usuario);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT) //codigo 204, indica que no tiene contenido
    @DeleteMapping("{id}")
    public void borrarUsuario(@PathVariable String id){
        usuarioService.deleteUsuario(id);
    }

}
