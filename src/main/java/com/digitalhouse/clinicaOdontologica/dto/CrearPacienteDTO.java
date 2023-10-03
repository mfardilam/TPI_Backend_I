package com.digitalhouse.clinicaOdontologica.dto;

import com.digitalhouse.clinicaOdontologica.model.Domicilio;

import java.util.Date;

public record CrearPacienteDTO(String nombre,
                               String apellido,
                               String dni,
                               Date fechaAlta,
                               CrearDomicilioDTO domicilio
                               //Domicilio domicilio

) {
}
