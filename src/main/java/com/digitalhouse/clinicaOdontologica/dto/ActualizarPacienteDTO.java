package com.digitalhouse.clinicaOdontologica.dto;

import java.util.Date;

public record ActualizarPacienteDTO(Long id,
                                    String nombre,
                                    String apellido,
                                    String dni,
                                    Date fechaAlta,
                                    DomicilioDTO domicilio) {
}
