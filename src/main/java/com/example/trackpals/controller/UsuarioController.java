package com.example.trackpals.controller;

import com.example.trackpals.exception.AttributeException;
import com.example.trackpals.exception.ResourceNotFoundException;
import com.example.trackpals.model.Usuario;
import com.example.trackpals.service.UsuarioService;
import jakarta.validation.Valid;
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

    //Buscar todos los usuarios
    @GetMapping("")
    public List<Usuario> listarUsuarios(){
        return usuarioService.getAllUsuarios();
    }

    //Buscar un usuario por su id
    @GetMapping("{id}")
    public Usuario obtenerUsuario(@PathVariable String id) throws ResourceNotFoundException {
        return usuarioService.getUsuarioById(id);
    }

    //Buscar usuarios cuyo nombre contenga la cadena a buscar
    @GetMapping("buscar/{nombre}")
    public List<Usuario> buscarUsuarios(@PathVariable String nombre){
        return usuarioService.searchUsuariosByName(nombre);
    }

    @ResponseStatus(HttpStatus.CREATED) //codigo 201, indica que se ha creado algo
    @PostMapping("")
    public Usuario crearUsuario(@Valid @RequestBody Usuario usuario) throws AttributeException {
        return usuarioService.createUsuario(usuario);
    }

    @PutMapping("{id}")
    public Usuario actualizarUsuario(@PathVariable String id, @Valid @RequestBody Usuario usuario) throws ResourceNotFoundException {
        return usuarioService.updateUsuario(id, usuario);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT) //codigo 204, indica que no tiene contenido
    @DeleteMapping("{id}")
    public void borrarUsuario(@PathVariable String id) throws ResourceNotFoundException {
        usuarioService.deleteUsuario(id);
    }

}
