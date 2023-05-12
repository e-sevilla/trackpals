package com.example.trackpals.controller;

import com.example.trackpals.dto.ExcursionDto;
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

import java.util.ArrayList;
import java.util.List;

@CrossOrigin //habilita CORS para que el cliente Angular (u otro??) pueda consultar la API sin errores
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

    //Buscar todas las rutas
    @GetMapping("/rutas/{id}")
    public Ruta obtenerRuta(@PathVariable String id) throws ResourceNotFoundException {
        return rutaService.getRutaById(id);
    }

    //Buscar todas las excursiones
    @GetMapping("")
    public List<ExcursionDto> listarExcursiones(){
        List<ExcursionDto> excursionDtoList = new ArrayList<>();
        List<Excursion> excursionList = excursionService.getAllExcursiones();
        for (Excursion excursion : excursionList) {
            excursionDtoList.add(new ExcursionDto(excursion));
        }
        return excursionDtoList;
    }

    //Buscar una excursion por su id (devuelve la excursion menos sus mensajes)
    @GetMapping("{id}")
    public Excursion obtenerExcursion(@PathVariable String id) throws ResourceNotFoundException {
        Excursion foundExcursion = excursionService.getExcursionById(id);
        foundExcursion.setMensajes(null);
        return foundExcursion;
    }

    //Buscar los mensajes de una excursion por su id
    @GetMapping("{id}/mensaje")
    public List<Mensaje> obtenerMensajesExcursion(@PathVariable String id) throws ResourceNotFoundException {
        Excursion excursion = excursionService.getExcursionById(id);
        return excursion.getMensajes();
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
        else if (excursion.getRuta() != null && excursion.getRuta().getId() != null){
            Ruta foundRuta = rutaService.getRutaById(excursion.getRuta().getId());
            excursion.setRuta(foundRuta);
        }
        Excursion excursionCreada = excursionService.createExcursion(excursion);
        foundUsuario.getIdsExcursionesApuntado().add(excursionCreada.getId());
        usuarioService.updateUsuario(foundUsuario.getId(), foundUsuario);
        return excursionCreada;
    }

    //Añade un mensaje a una excursion
    @ResponseStatus(HttpStatus.CREATED) //codigo 201, indica que se ha creado algo
    @PostMapping("{id}/mensaje")
    public List<Mensaje> nuevoMensaje(@PathVariable String id, @Valid @RequestBody Mensaje mensaje) throws ResourceNotFoundException {
        Usuario foundUsuario = usuarioService.getUsuarioById(mensaje.getAutor().getId());
        mensaje.setAutor(foundUsuario);
        mensajeService.createMensaje(mensaje);
        Excursion foundExcursion = excursionService.getExcursionById(id);
        foundExcursion.getMensajes().add(mensaje);
        Excursion excursionModificada = excursionService.updateExcursion(id, foundExcursion);
        return excursionModificada.getMensajes();
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
