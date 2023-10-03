package com.digitalhouse.clinicaOdontologica.controller;

import com.digitalhouse.clinicaOdontologica.dto.ActualizarPacienteDTO;
import com.digitalhouse.clinicaOdontologica.dto.CrearPacienteDTO;
import com.digitalhouse.clinicaOdontologica.dto.PacienteDTO;
import com.digitalhouse.clinicaOdontologica.service.PacienteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private final Logger LOGGER = Logger.getLogger(PacienteController.class);
    private final PacienteService service;
    @Autowired
    public PacienteController(PacienteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> crearPaciente(@RequestBody CrearPacienteDTO crearPacienteDTO){
        LOGGER.info("Recibí una solicitud para crear al paciente "+crearPacienteDTO.nombre());
        LOGGER.info("La info del front fue: " + crearPacienteDTO);

        try{
            service.crearPaciente(crearPacienteDTO);
            LOGGER.info("Paciente creado exitosamente");
            return ResponseEntity.ok(HttpStatus.CREATED);
        }catch (Exception e){
            LOGGER.error("Error al persistir el paciente", e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<Object> buscarPaciente(@PathVariable Long id){
        LOGGER.info("Recibí una solicitud para buscar al paciente con ID "+id);
        PacienteDTO pacienteDTO;

        pacienteDTO = service.buscarPaciente(id);
        LOGGER.info("Encontré al paciente "+ pacienteDTO.getNombre() + "con ID "+pacienteDTO.getId());

        return ResponseEntity.ok(pacienteDTO);
    }

    @GetMapping
    public ResponseEntity<Object> listarPacientes(){
        LOGGER.info("Recibí una solicitud para listar los pacientes");
        List<PacienteDTO> pacientes;
        try{
            pacientes = service.listarPacientes();
            LOGGER.info("Encontré un total de "+pacientes.size()+" pacientes");
        }catch (Exception e){
            LOGGER.error("Ocurrió un error al listar los pacientes", e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.ok(pacientes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> borrarPaciente(@PathVariable Long id){
        LOGGER.info("Recibí una solicitud para borra al paciente con ID "+id);

        service.borrarPaciente(id);
        LOGGER.info("Paciente con ID "+ id +" eliminado exitosamente");
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<Object> actualizarPaciente(@RequestBody ActualizarPacienteDTO actualizarPacienteDTO){
        LOGGER.info("Recibí solicitud para actualizar paciente con ID " +actualizarPacienteDTO.id());

        service.actualizarPaciente(actualizarPacienteDTO);
        LOGGER.info("Paciente con ID " + actualizarPacienteDTO.id() + "actualizado exitosamente");
        return ResponseEntity.ok(HttpStatus.OK);

    }

}
