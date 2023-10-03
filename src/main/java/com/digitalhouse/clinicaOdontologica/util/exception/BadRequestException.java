package com.digitalhouse.clinicaOdontologica.util.exception;

import org.springframework.beans.factory.annotation.Autowired;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }
}
