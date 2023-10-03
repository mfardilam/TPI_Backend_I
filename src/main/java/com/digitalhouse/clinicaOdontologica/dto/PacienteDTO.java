package com.digitalhouse.clinicaOdontologica.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;



@Getter
@Setter
public class PacienteDTO {

    private Long id;
    private String nombre;

    private String apellido;

    private String dni;

    private Date fechaAlta;

    private Long domicilio_id;



    public PacienteDTO() {
    }

    public PacienteDTO(Long id, String nombre, String apellido, String dni, Date fechaAlta, Long domicilio_id) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaAlta = fechaAlta;
        this.domicilio_id = domicilio_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Long getDomicilio_id() {
        return domicilio_id;
    }

    public void setDomicilio_id(Long domicilio_id) {
        this.domicilio_id = domicilio_id;
    }
}
