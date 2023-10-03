package com.digitalhouse.clinicaOdontologica.service;

import com.digitalhouse.clinicaOdontologica.dto.ActualizarPacienteDTO;
import com.digitalhouse.clinicaOdontologica.dto.CrearPacienteDTO;
import com.digitalhouse.clinicaOdontologica.dto.DomicilioDTO;
import com.digitalhouse.clinicaOdontologica.dto.PacienteDTO;
import com.digitalhouse.clinicaOdontologica.model.Paciente;
import com.digitalhouse.clinicaOdontologica.repository.PacienteRepository;
import com.digitalhouse.clinicaOdontologica.util.Mapper;
import com.digitalhouse.clinicaOdontologica.util.exception.ResoruceNotFoundException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PacienteService {
    private final PacienteRepository pacienteRepository;
    private final DomicilioService domicilioService;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository, DomicilioService domicilioService) {
        this.pacienteRepository = pacienteRepository;
        this.domicilioService = domicilioService;
    }

    public Paciente crearPaciente(CrearPacienteDTO crearPacienteDTO){
        Paciente paciente = new Paciente();

        paciente.setNombre(crearPacienteDTO.nombre());
        paciente.setApellido(crearPacienteDTO.apellido());
        paciente.setDni(crearPacienteDTO.dni());
        paciente.setFechaAlta(crearPacienteDTO.fechaAlta());
        paciente.setDomicilio(domicilioService.crearDomicilio(crearPacienteDTO.domicilio()));
        //paciente.setDomicilio(crearPacienteDTO.domicilio());

        pacienteRepository.save(paciente);
        return paciente;
    }

    public List<PacienteDTO> listarPacientes(){
        List<PacienteDTO> resultado = new ArrayList<>();

        List<Paciente> pacientes = pacienteRepository.findAll();

        for (Paciente paciente: pacientes) {
            resultado.add(Mapper.mapPacienteDTO(paciente));
        }

        return resultado;
    }

    public PacienteDTO buscarPaciente(Long id){

        PacienteDTO pacienteDTO;
        if(!pacienteRepository.existsById(id)) throw new ResoruceNotFoundException(id.toString(), "Paciente ID");
        pacienteDTO = Mapper.mapPacienteDTO(pacienteRepository.findById(id).orElse(null));

        return pacienteDTO;
    }

    public void borrarPaciente(Long id){
        //Excepción de recurso no encontrado
        if(!pacienteRepository.existsById(id)) throw new ResoruceNotFoundException(id.toString(), "Paciente ID");
        pacienteRepository.deleteById(id);
    }

    public void actualizarPaciente(ActualizarPacienteDTO pacienteDTO){
        //Excepción de recurso no encontrado
        if(!pacienteRepository.existsById(pacienteDTO.id())) throw new ResoruceNotFoundException(pacienteDTO.id().toString(), "Paciente ID");
        pacienteRepository.actualizarPaciente(pacienteDTO.nombre(), pacienteDTO.nombre(),
                pacienteDTO.dni(), pacienteDTO.fechaAlta(), pacienteDTO.id());

        domicilioService.actualizarDomicilio(pacienteDTO.domicilio());
    }

    public boolean existe(Long id){
        return pacienteRepository.existsById(id);
    }
}
