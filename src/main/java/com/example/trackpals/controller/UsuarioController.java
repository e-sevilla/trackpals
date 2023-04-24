package com.example.trackpals.controller;

import com.example.trackpals.dto.UsuarioGlobalDto;
import com.example.trackpals.dto.UsuarioPerfilDto;
import com.example.trackpals.dto.UsuarioRegistroDto;
import com.example.trackpals.exception.AttributeException;
import com.example.trackpals.exception.ResourceNotFoundException;
import com.example.trackpals.model.Usuario;
import com.example.trackpals.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
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

    //Buscar usuarios cuyo nombre contenga la cadena a buscar (case insensitive)
    @GetMapping("buscar/{nombre}")
    public List<Usuario> buscarUsuarios(@PathVariable String nombre){
        return usuarioService.searchUsuariosByName(nombre);
    }

    //Buscar un perfil de usuario por su id
    @GetMapping("perfil/{id}")
    public UsuarioPerfilDto obtenerPerfil(@PathVariable String id) throws ResourceNotFoundException {
        Usuario usuario = usuarioService.getUsuarioById(id);
        return new UsuarioPerfilDto(usuario);
    }

    //Modificar el perfil de un usuario
    @PostMapping("perfil")
    public UsuarioPerfilDto actualizarPerfil(@RequestBody UsuarioPerfilDto usuarioPerfilDto) throws ResourceNotFoundException {
        Usuario foundUsuario = usuarioService.getUsuarioById(usuarioPerfilDto.getId());
        foundUsuario.setFechaNac(usuarioPerfilDto.getFechaNac());
        foundUsuario.setDireccion(usuarioPerfilDto.getDireccion());
        foundUsuario.setDescripcion(usuarioPerfilDto.getDescripcion());
        foundUsuario.setFoto(usuarioPerfilDto.getFoto());
        return new UsuarioPerfilDto(usuarioService.updateUsuario(usuarioPerfilDto.getId(), foundUsuario));
    }

    //Crear un usuario
    @ResponseStatus(HttpStatus.CREATED) //codigo 201, indica que se ha creado algo
    @PostMapping("")
    public Usuario crearUsuario(@Valid @RequestBody UsuarioRegistroDto usuarioRegistroDto) throws AttributeException, NoSuchAlgorithmException {
        usuarioRegistroDto.setContrasenia(usuarioService.encriptarContrasenia(usuarioRegistroDto.getContrasenia()));
        return usuarioService.createUsuario(new Usuario(usuarioRegistroDto));
    }

    //Login de un usuario
    @PostMapping("login")
    public UsuarioGlobalDto loginUsuario(@RequestBody UsuarioRegistroDto usuarioRegistroDto) throws Exception {
        Usuario usuario = usuarioService.getUsuarioByName(usuarioRegistroDto.getNombre());
        if (!usuario.getContrasenia().equals(usuarioService.encriptarContrasenia(usuarioRegistroDto.getContrasenia()))) {
            throw new Exception("Contraseña incorrecta");
        }
        return new UsuarioGlobalDto(usuario);
    }

    //Añadir un amigo a un usuario
    @GetMapping("seguir-amigo/{idUsuario}/{idAmigo}")
    public List<String> seguirUsuario(@PathVariable String idUsuario, @PathVariable String idAmigo) throws ResourceNotFoundException {
        Usuario usuario = usuarioService.getUsuarioById(idUsuario);
        if ((!idUsuario.equals(idAmigo)) && (!usuario.getIdsAmigos().contains(idAmigo))){
            usuario.getIdsAmigos().add(idAmigo);
            usuarioService.updateUsuario(idUsuario, usuario);
        }
        return usuario.getIdsAmigos();
    }

    //Quitar un amigo de un usuario
    @GetMapping("quitar-amigo/{idUsuario}/{idAmigo}")
    public List<String> quitarUsuario(@PathVariable String idUsuario, @PathVariable String idAmigo) throws ResourceNotFoundException {
        Usuario usuario = usuarioService.getUsuarioById(idUsuario);
        if (usuario.getIdsAmigos().contains(idAmigo)){
            usuario.getIdsAmigos().remove(idAmigo);
            usuarioService.updateUsuario(idUsuario, usuario);
        }
        return usuario.getIdsAmigos();
    }

    //Añadir una excursion a un usuario
    @GetMapping("seguir-excursion/{idUsuario}/{idExcursion}")
    public List<String> seguirExcursion(@PathVariable String idUsuario, @PathVariable String idExcursion) throws ResourceNotFoundException {
        Usuario usuario = usuarioService.getUsuarioById(idUsuario);
        if (!usuario.getIdsExcursionesApuntado().contains(idExcursion)){
            usuario.getIdsExcursionesApuntado().add(idExcursion);
            usuarioService.updateUsuario(idUsuario, usuario);
        }
        return usuario.getIdsExcursionesApuntado();
    }

    //Quitar una excursion de un usuario
    @GetMapping("quitar-excursion/{idUsuario}/{idExcursion}")
    public List<String> quitarExcursion(@PathVariable String idUsuario, @PathVariable String idExcursion) throws ResourceNotFoundException {
        Usuario usuario = usuarioService.getUsuarioById(idUsuario);
        if (usuario.getIdsExcursionesApuntado().contains(idExcursion)){
            usuario.getIdsExcursionesApuntado().remove(idExcursion);
            usuarioService.updateUsuario(idUsuario, usuario);
        }
        return usuario.getIdsExcursionesApuntado();
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
