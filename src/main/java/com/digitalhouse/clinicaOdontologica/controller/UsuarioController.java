package com.digitalhouse.clinicaOdontologica.controller;


import com.digitalhouse.clinicaOdontologica.dto.UsuarioDTO;
import com.digitalhouse.clinicaOdontologica.service.UsuarioService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final Logger LOGGER = Logger.getLogger(UsuarioController.class);
    private final UsuarioService service;

    @Autowired
    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> registrarUsuario(@RequestBody UsuarioDTO usuarioDTO){
        LOGGER.info("Recibí una solicitud para crear al usuario ID"+usuarioDTO.getId());
        LOGGER.info("La info del front fue: " + usuarioDTO.toString());

        try{
            service.crearUsuario(usuarioDTO);
            LOGGER.info("Usuario creado exitoxamente");
            return ResponseEntity.ok(HttpStatus.CREATED);
        } catch (Exception e){
            LOGGER.error("Error al persistir el usuario ",e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> listarUsuarios(){
        LOGGER.info("Recibí una solicitud para listar los usuarios");
        List<UsuarioDTO> usuarios;

        try{
            usuarios = service.listarUsuarios();
            LOGGER.info("Encontré un total de "+usuarios.size()+" usuarios");
        }catch (Exception e){
            LOGGER.error("Ocurrió un error al listar los usuarios", e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<Object> buscarUsuario(@PathVariable Long id){
        LOGGER.info("Recibí una solicitud para buscar al usuario con ID "+id);
        UsuarioDTO usuarioDTO;

        usuarioDTO = service.buscarUsuario(id);
        LOGGER.info("Encontré al ususario con ID "+id);

        return ResponseEntity.ok(usuarioDTO);
    }
}
