package com.digitalhouse.clinicaOdontologica.service;

import com.digitalhouse.clinicaOdontologica.dto.OdontologoDTO;
import com.digitalhouse.clinicaOdontologica.dto.TurnoDTO;
import com.digitalhouse.clinicaOdontologica.model.Odontologo;
import com.digitalhouse.clinicaOdontologica.model.Paciente;
import com.digitalhouse.clinicaOdontologica.model.Turno;
import com.digitalhouse.clinicaOdontologica.repository.TurnoRepository;
import com.digitalhouse.clinicaOdontologica.util.Mapper;
import com.digitalhouse.clinicaOdontologica.util.exception.BadRequestException;
import com.digitalhouse.clinicaOdontologica.util.exception.ResoruceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TurnoService {
    private final TurnoRepository turnoRepository;
    private final PacienteService pacienteService;
    private final OdontologoService odontologoService;
    private final ObjectMapper mapper;

    private static final ArrayList<String> dateList = new ArrayList<>();

    @Autowired
    public TurnoService(TurnoRepository turnoRepository, PacienteService pacienteService, OdontologoService odontologoService, ObjectMapper mapper) {
        this.turnoRepository = turnoRepository;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
        this.mapper = mapper;
    }

    public Turno crearTurno(TurnoDTO turnoDTO){
        Turno turno = new Turno();
        Odontologo odontologo;
        Paciente paciente;

        //Validando si existen paciente y odontologo

        if(!odontologoService.existe(turnoDTO.getOdontologo_id())){
            throw new BadRequestException("El odontólogo con el que desea agendar no exite");
        }

        if(!pacienteService.existe(turnoDTO.getPaciente_id())){
            throw new BadRequestException("El paciente con el que desea agendar no existe");
        }

        //Validando si estan disponibles las entidades
        if(turnoRepository.disponibilidadOdontologoFecha(turnoDTO.getOdontologo_id(), turnoDTO.getFecha()) > 0){
            throw new BadRequestException("El odontólogo ya cuenta con un turno para la fecha ingresada");
        }


        odontologo = mapper.convertValue(odontologoService.buscarOdontologo(turnoDTO.getOdontologo_id()),Odontologo.class);
        paciente = mapper.convertValue(pacienteService.buscarPaciente(turnoDTO.getPaciente_id()),Paciente.class);

        turno.setFecha(turnoDTO.getFecha());
        turno.setOdontologo(odontologo);
        turno.setPaciente(paciente);

        turnoRepository.save(turno);

        return turno;
    }

    public List<TurnoDTO> listarTurnos(){
        List<TurnoDTO> resultado = new ArrayList<>();

        List<Turno> turnos = turnoRepository.findAll();
        for (Turno turno: turnos) {
            resultado.add(Mapper.mapTurnoDTO(turno));
        }

        return resultado;
    }

    public TurnoDTO buscarTurno(Long id){

        if(!turnoRepository.existsById(id)) throw new ResoruceNotFoundException(id.toString(), "Turno ID");
        return Mapper.mapTurnoDTO(turnoRepository.findById(id).orElse(null));
    }

    public void borrarTurno(Long id){
        if(!turnoRepository.existsById(id)) throw new ResoruceNotFoundException(id.toString(), "Turno ID");
        turnoRepository.deleteById(id);
    }



}
