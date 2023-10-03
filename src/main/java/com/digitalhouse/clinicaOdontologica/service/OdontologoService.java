package com.digitalhouse.clinicaOdontologica.service;


import com.digitalhouse.clinicaOdontologica.controller.OdontologoController;
import com.digitalhouse.clinicaOdontologica.dto.CrearOdontologoDTO;
import com.digitalhouse.clinicaOdontologica.dto.OdontologoDTO;
import com.digitalhouse.clinicaOdontologica.model.Odontologo;
import com.digitalhouse.clinicaOdontologica.repository.OdontologoRepository;
import com.digitalhouse.clinicaOdontologica.util.exception.ResoruceNotFoundException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService {
    private final OdontologoRepository odontologoRepository;
    private final Logger LOGGER = Logger.getLogger(OdontologoService.class);
    private final ObjectMapper mapper;
    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository, ObjectMapper mapper) {
        this.odontologoRepository = odontologoRepository;
        this.mapper = mapper;
    }

    public Odontologo crearOdontologo(CrearOdontologoDTO odontologoDTO){
        Odontologo odontologo = new Odontologo();

        odontologo.setNombre(odontologoDTO.nombre());
        odontologo.setApellido(odontologoDTO.apellido());
        odontologo.setMatricula(odontologoDTO.matricula());


        odontologoRepository.save(odontologo);
        LOGGER.info("Ondontologo creado");
        return odontologo;
    }

    public List<OdontologoDTO> listarOdontologos(){


        List<Odontologo> odontologos = odontologoRepository.findAll();

        return mapper.convertValue(odontologos, new TypeReference<>(){});
    }

    public OdontologoDTO buscarOdontologo(Long id){
        OdontologoDTO odontologoDTO;

        odontologoDTO = mapper.convertValue(odontologoRepository.findById(id),OdontologoDTO.class);
        if(odontologoDTO == null) throw new ResoruceNotFoundException(id.toString(), "Odontologo ID");

        return odontologoDTO;
    }

    public void borrarOdontologo(Long id){
        if(!odontologoRepository.existsById(id)) throw new ResoruceNotFoundException(id.toString(), "Odontologo ID");
        odontologoRepository.deleteById(id);
    }

    public void actualizarOdontologo(OdontologoDTO odontologoDTO){
        if(!odontologoRepository.existsById(odontologoDTO.getId())) throw new ResoruceNotFoundException(odontologoDTO.getId().toString(), "Odontologo ID");
        odontologoRepository.actualizarOdontologo(odontologoDTO.getNombre(), odontologoDTO.getApellido(),
                odontologoDTO.getMatricula(),odontologoDTO.getId());

    }

    public boolean existe(Long id){
        return odontologoRepository.existsById(id);
    }
}
