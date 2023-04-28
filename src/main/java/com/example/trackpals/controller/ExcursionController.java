package com.example.trackpals.controller;

import com.example.trackpals.exception.ResourceNotFoundException;
import com.example.trackpals.model.Excursion;
import com.example.trackpals.model.Mensaje;
import com.example.trackpals.model.Ruta;
import com.example.trackpals.model.Usuario;
import com.example.trackpals.service.ExcursionService;
import com.example.trackpals.service.MensajeService;
import com.example.trackpals.service.RutaService;
import com.example.trackpals.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/excursiones")
public class ExcursionController {

    @Autowired
    ExcursionService excursionService;
    @Autowired
    RutaService rutaService;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    MensajeService mensajeService;

    //Buscar todas las rutas
    @GetMapping("/rutas")
    public List<Ruta> listarRutas(){
        return rutaService.getAllRutas();
    }

    //Buscar todas las excursiones
    @GetMapping("")
    public List<Excursion> listarExcursiones(){
        return excursionService.getAllExcursiones();
    }

    //Buscar una excursion por su id
    @GetMapping("{id}")
    public Excursion obtenerExcursion(@PathVariable String id) throws ResourceNotFoundException {
        return excursionService.getExcursionById(id);
    }

    //Crear una excursion
    @ResponseStatus(HttpStatus.CREATED) //codigo 201, indica que se ha creado algo
    @PostMapping("")
    public Excursion crearExcursion(@Valid @RequestBody Excursion excursion) throws ResourceNotFoundException {
        Usuario foundUsuario = usuarioService.getUsuarioById(excursion.getCreador().getId());
        excursion.setCreador(foundUsuario);
        if (excursion.getRuta() != null && excursion.getRuta().getId() == null){
            rutaService.createRuta(excursion.getRuta());
        }
        Excursion excursionCreada = excursionService.createExcursion(excursion);
        foundUsuario.getIdsExcursionesApuntado().add(excursionCreada.getId());
        usuarioService.updateUsuario(foundUsuario.getId(), foundUsuario);
        return excursionCreada;
    }

    //Añade un mensaje a una excursion
    @ResponseStatus(HttpStatus.CREATED) //codigo 201, indica que se ha creado algo
    @PostMapping("{id}/mensaje")
    public Excursion nuevoMensaje(@PathVariable String id, @Valid @RequestBody Mensaje mensaje) throws ResourceNotFoundException {
        Usuario foundUsuario = usuarioService.getUsuarioById(mensaje.getAutor().getId());
        mensaje.setAutor(foundUsuario);
        mensajeService.createMensaje(mensaje);
        Excursion foundExcursion = excursionService.getExcursionById(id);
        foundExcursion.getMensajes().add(mensaje);
        return excursionService.updateExcursion(id, foundExcursion);
    }


    //Actualiza los datos de una excursion
    @PutMapping("{id}")
    public Excursion actualizarExcursion(@PathVariable String id, @Valid @RequestBody Excursion excursion) throws ResourceNotFoundException {
        if (excursion.getRuta() != null && excursion.getRuta().getId() == null){
            rutaService.createRuta(excursion.getRuta());
        }
        return excursionService.updateExcursion(id, excursion);
    }

    //Elimina una excursion
    @ResponseStatus(HttpStatus.NO_CONTENT) //codigo 204, indica que no tiene contenido
    @DeleteMapping("{id}")
    public void borrarExcursion(@PathVariable String id) throws ResourceNotFoundException {
        Excursion foundExcursion = excursionService.getExcursionById(id);
        for (Mensaje mens : foundExcursion.getMensajes()) {
            mensajeService.deleteMensaje(mens);
        }
        for (Usuario foundUsuario : usuarioService.getAllUsuarios()) {
            if (foundUsuario.getIdsExcursionesApuntado().contains(id)) {
                foundUsuario.getIdsExcursionesApuntado().remove(id);
                usuarioService.updateUsuario(foundUsuario.getId(), foundUsuario);
            }
        }
        excursionService.deleteExcursion(id);
    }

}
