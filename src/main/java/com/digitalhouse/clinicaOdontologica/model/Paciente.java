package com.digitalhouse.clinicaOdontologica.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PACIENTES")
@Getter
@Setter
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    private String apellido;

    private String dni;
    @Column(name = "FECHA_ALTA")
    private Date fechaAlta;

    //@OneToOne(cascade = CascadeType.ALL)
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "domicilio_id", referencedColumnName = "id")
    @JsonIgnore
    private Domicilio domicilio;
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Turno> turnos = new ArrayList<>();

    public Paciente() {
    }

    public Paciente(String nombre, String apellido, String dni, Date fechaAlta, Domicilio domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaAlta = fechaAlta;
        this.domicilio = domicilio;
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

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }
}
