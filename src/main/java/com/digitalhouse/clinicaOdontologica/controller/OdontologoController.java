package com.digitalhouse.clinicaOdontologica.controller;


import com.digitalhouse.clinicaOdontologica.dto.CrearOdontologoDTO;
import com.digitalhouse.clinicaOdontologica.dto.OdontologoDTO;
import com.digitalhouse.clinicaOdontologica.service.OdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private final Logger LOGGER = Logger.getLogger(OdontologoController.class);
    private final OdontologoService service;
    @Autowired
    public OdontologoController(OdontologoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> crearOdontologo(@RequestBody CrearOdontologoDTO crearOdontologoDTO){
        LOGGER.info("Recibí una solicitud para crear al odontologo "+crearOdontologoDTO.nombre());
        LOGGER.info("La info del front fue: " + crearOdontologoDTO);

        try{
            service.crearOdontologo(crearOdontologoDTO);
            LOGGER.info("El odontólogo ha sido creado exitosamente");
            return ResponseEntity.ok(HttpStatus.CREATED);
        }catch (Exception e){
            LOGGER.error("Error al persistir el odontologo", e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<Object> buscarOdontologo(@PathVariable Long id){
        LOGGER.info("Recibí una solicitud para buscar al odontologo con ID "+id);
        OdontologoDTO odontologoDTO;

        odontologoDTO = service.buscarOdontologo(id);
        LOGGER.info("Encontré al odontologo "+ odontologoDTO.getNombre() + "con ID "+odontologoDTO.getId());

        return ResponseEntity.ok(odontologoDTO);
    }


    @GetMapping()
    public ResponseEntity<Object> listarOdontologo(){
        LOGGER.info("Recibí una solicitud para listar los odontologos");
        List<OdontologoDTO> odontologos;
        try{
            odontologos = service.listarOdontologos();
            LOGGER.info("Encontré un total de "+odontologos.size()+" odontologos");
        }catch (Exception e){
            LOGGER.error("Ocurrió un error al listar los odontologos", e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.ok(odontologos);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> borrarOdontologo(@PathVariable Long id){
        //ResponseEntity<Object>
        LOGGER.info("Recibí una solicitud para borrar odontologo con ID "+id);

        service.borrarOdontologo(id);
        LOGGER.info("Odontologo con ID "+ id + " eliminado exitosamente");
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<Object> actualizarOdontologo(@RequestBody OdontologoDTO odontologoDTO){
        LOGGER.info("Recibí solicitud para actualizar odontologo con ID " +odontologoDTO.getId());

        service.actualizarOdontologo(odontologoDTO);
        LOGGER.info("Paciente con ID " +odontologoDTO.getId()+ "actualizado exitosamente");
        return ResponseEntity.ok(odontologoDTO);

    }


}
