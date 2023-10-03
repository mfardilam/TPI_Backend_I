package com.digitalhouse.clinicaOdontologica.controller;

import com.digitalhouse.clinicaOdontologica.dto.DomicilioDTO;
import com.digitalhouse.clinicaOdontologica.dto.ErrorDTO;
import com.digitalhouse.clinicaOdontologica.service.DomicilioService;
import com.digitalhouse.clinicaOdontologica.util.exception.ResoruceNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/domicilios")
public class DomicilioController {
    private final Logger LOGGER = Logger.getLogger(DomicilioController.class);
    private final DomicilioService service;
    @Autowired
    public DomicilioController(DomicilioService service) {
        this.service = service;
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<Object> buscarDomicilio(@PathVariable Long id){
        LOGGER.info("Recibí una solicitud para buscar al domicilio con ID "+id);
        DomicilioDTO domicilioDTO;

        domicilioDTO = service.buscarDomicilio(id);
        LOGGER.info("Encontré al domiclio con ID "+id);

        return ResponseEntity.ok(domicilioDTO);
    }
    @GetMapping()
    public ResponseEntity<Object> listarDomicilios(){
        LOGGER.info("Recibí una solicitud para listar los domicilios");
        List<DomicilioDTO> domicilios;
        try{
            domicilios = service.listarDomicilios();
            LOGGER.info("Encontré un total de "+domicilios.size()+" domicilios");
        }catch (Exception e){
            LOGGER.error("Ocurrió un error al listar los domiclios", e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.ok(domicilios);
    }


}
