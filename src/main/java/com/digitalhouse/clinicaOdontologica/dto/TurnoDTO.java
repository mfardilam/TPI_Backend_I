package com.digitalhouse.clinicaOdontologica.dto;

import java.util.Date;

public class TurnoDTO {
    private Long id;
    private Long paciente_id;
    private Long odontologo_id;
    private Date fecha;

    public TurnoDTO() {
    }

    public TurnoDTO(Long paciente_id, Long odontologo_id, Date fecha) {
        this.paciente_id = paciente_id;
        this.odontologo_id = odontologo_id;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPaciente_id() {
        return paciente_id;
    }

    public void setPaciente_id(Long paciente_id) {
        this.paciente_id = paciente_id;
    }

    public Long getOdontologo_id() {
        return odontologo_id;
    }

    public void setOdontologo_id(Long odontologo_id) {
        this.odontologo_id = odontologo_id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
