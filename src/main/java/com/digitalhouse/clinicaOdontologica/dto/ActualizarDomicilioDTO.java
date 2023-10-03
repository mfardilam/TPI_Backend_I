package com.digitalhouse.clinicaOdontologica.dto;

public record ActualizarDomicilioDTO(Long id,
                                     String calle,
                                     String numero,
                                     String localidad,
                                     String provincia) {
}
