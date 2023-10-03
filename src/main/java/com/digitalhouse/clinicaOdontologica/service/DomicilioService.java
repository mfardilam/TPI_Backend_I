package com.digitalhouse.clinicaOdontologica.service;

import com.digitalhouse.clinicaOdontologica.dto.CrearDomicilioDTO;
import com.digitalhouse.clinicaOdontologica.dto.DomicilioDTO;
import com.digitalhouse.clinicaOdontologica.model.Domicilio;
import com.digitalhouse.clinicaOdontologica.repository.DomicilioRepository;
import com.digitalhouse.clinicaOdontologica.util.exception.ResoruceNotFoundException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DomicilioService {
    private final DomicilioRepository domicilioRepository;
    private final ObjectMapper mapper;
    @Autowired
    public DomicilioService(DomicilioRepository domicilioRepository, ObjectMapper mapper) {
        this.domicilioRepository = domicilioRepository;
        this.mapper = mapper;
    }


    public Domicilio crearDomicilio(CrearDomicilioDTO domicilioDTO){

        Domicilio domicilio = new Domicilio();
        domicilio.setCalle(domicilioDTO.calle());
        domicilio.setNumero(domicilioDTO.numero());
        domicilio.setLocalidad(domicilioDTO.localidad());
        domicilio.setProvincia(domicilioDTO.provincia());

        domicilioRepository.save(domicilio);

        return domicilio;
    }

    public List<DomicilioDTO> listarDomicilios(){
        List<Domicilio> domicilios = domicilioRepository.findAll();

        return mapper.convertValue(domicilios, new TypeReference<>(){});
    }

    public DomicilioDTO buscarDomicilio(Long id){
        DomicilioDTO domicilioDTO;
        if(!domicilioRepository.existsById(id))throw new ResoruceNotFoundException(id.toString(), "Domicilio ID");
        domicilioDTO = mapper.convertValue(domicilioRepository.findById(id),DomicilioDTO.class);

        return domicilioDTO;
    }

    public void borrarDomicilio(Long id){
        if(!domicilioRepository.existsById(id)) throw new ResoruceNotFoundException(id.toString(), "Domicilio ID");
        domicilioRepository.deleteById(id);
    }

    public void actualizarDomicilio(DomicilioDTO domicilioDTO){
        if(!domicilioRepository.existsById(domicilioDTO.getId())) throw new ResoruceNotFoundException(domicilioDTO.getId().toString(), "Domicilio ID");
        domicilioRepository.actualizarDomicilio(domicilioDTO.getCalle(), domicilioDTO.getNumero(),
                domicilioDTO.getLocalidad(), domicilioDTO.getProvincia(), domicilioDTO.getId());
    }
}
