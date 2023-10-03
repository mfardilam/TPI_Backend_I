package com.digitalhouse.clinicaOdontologica.controller;

import com.digitalhouse.clinicaOdontologica.dto.ErrorDTO;
import com.digitalhouse.clinicaOdontologica.util.exception.BadRequestException;
import com.digitalhouse.clinicaOdontologica.util.exception.ResoruceNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionHandlerController {
    private final Logger LOGGER = Logger.getLogger(ExceptionHandlerController.class);
    @ExceptionHandler(ResoruceNotFoundException.class)
    public ResponseEntity<ErrorDTO> recursoNoEncontradoHandler(ResoruceNotFoundException exception){
        String mensaje = "Recurso no encontrado. "+ exception.getResource() + ": "+exception.getResourceId();
        LOGGER.error("Error al borrar la entidad "+ mensaje);
        ErrorDTO errorDTO = new ErrorDTO(HttpStatus.NOT_FOUND.toString(),mensaje);
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDTO> odontologoOpacienteNoEncontradoHandler(BadRequestException exception){
        String mensaje = "El paciente u odnotólogo con el que se desea agendar no existe";
        LOGGER.error("Error al crear turno " + mensaje);
        ErrorDTO errorDTO = new ErrorDTO(HttpStatus.BAD_REQUEST.toString(),mensaje);
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }
}
