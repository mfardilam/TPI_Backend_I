package com.digitalhouse.clinicaOdontologica.controller;


import com.digitalhouse.clinicaOdontologica.dto.TurnoDTO;
import com.digitalhouse.clinicaOdontologica.service.TurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private final Logger LOGGER = Logger.getLogger(TurnoController.class);
    private final TurnoService service;

    @Autowired
    public TurnoController(TurnoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> crearTurno(@RequestBody TurnoDTO turnoDTO){
        LOGGER.info("Recibí una solicitud para crear un turno ID "+turnoDTO.getId());
        LOGGER.info("La info del front fue: " + turnoDTO);

        service.crearTurno(turnoDTO);
        LOGGER.info("Turno creado exitoxamente");
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<Object> buscarTurno(@PathVariable Long id){
        LOGGER.info("Recibí una solicitud para buscar al paciente con ID "+id);
        TurnoDTO turnoDTO;

        try{
            turnoDTO = service.buscarTurno(id);
            LOGGER.info("Encontré al turno con ID "+id);

        }catch (Exception e){
            LOGGER.error("Error al buscar turno con ID "+id);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

        return ResponseEntity.ok(turnoDTO);
    }

    @GetMapping
    public ResponseEntity<Object> listarTurnos(){
        LOGGER.info("Recibí una solicitud para listar los turnos");
        List<TurnoDTO> turnos;

        try{
            turnos = service.listarTurnos();
            LOGGER.info("Encontré un total de "+turnos.size()+" turnos");
        }catch (Exception e){
            LOGGER.error("Ocurrió un error al listar los turnos", e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

        return ResponseEntity.ok(turnos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> borrarTurno(@PathVariable Long id){
        LOGGER.info("Recibí una solicitud para borrar turno con ID "+id);
        TurnoDTO turnoDTO = service.buscarTurno(id);

        if(turnoDTO == null){
            LOGGER.info("El turno con ID "+id+" no ha sido encontrado");
            return ResponseEntity.notFound().build();
        }

        try{
            service.borrarTurno(id);
            LOGGER.info("Turno con ID "+ id + " eliminado exitosamente");
            return ResponseEntity.ok(HttpStatus.OK);
        }catch (Exception e){
            LOGGER.error("Ha ocurrido un error al borra el turno con ID "+id);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }


}
