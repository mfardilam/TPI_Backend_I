package com.digitalhouse.clinicaOdontologica.util.exception;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Autowired;


@Getter
public class ResoruceNotFoundException extends RuntimeException{
    private final String resourceId;
    private final String resource;

    @Autowired
    public ResoruceNotFoundException(String resourceId, String resource) {
        this.resourceId = resourceId;
        this.resource = resource;
    }

    public String getResourceId() {
        return resourceId;
    }

    public String getResource() {
        return resource;
    }
}
