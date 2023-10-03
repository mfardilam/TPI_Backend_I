package com.digitalhouse.clinicaOdontologica.service;


import com.digitalhouse.clinicaOdontologica.controller.UsuarioController;
import com.digitalhouse.clinicaOdontologica.dto.UsuarioDTO;
import com.digitalhouse.clinicaOdontologica.model.Usuario;
import com.digitalhouse.clinicaOdontologica.repository.UsuarioRepository;
import com.digitalhouse.clinicaOdontologica.util.Mapper;
import com.digitalhouse.clinicaOdontologica.util.exception.ResoruceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final ObjectMapper mapper;
    private final Logger LOGGER = Logger.getLogger(UsuarioController.class);
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, ObjectMapper mapper) {
        this.usuarioRepository = usuarioRepository;
        this.mapper = mapper;
    }

    public Usuario crearUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario();

        try{
            usuario.setUsername(usuarioDTO.getUsername());
            usuario.setPassword(usuarioDTO.getPassword());
            usuario.setRol(usuarioDTO.getRol());
            usuarioRepository.save(usuario);
        }catch (Exception e){
            LOGGER.error("Error al persistir el usuario ",e);
            throw new RuntimeException("Error al persistir el usuario ",e);
        }

        return usuario;
    }
    public UsuarioDTO buscarUsuario(Long id){
        UsuarioDTO usuarioDTO;
        if(!usuarioRepository.existsById(id)) throw new ResoruceNotFoundException(id.toString(), "Usuario ID");
        usuarioDTO = Mapper.mapUsuarioDTO(usuarioRepository.findById(id).orElse(null));

        return usuarioDTO;
    }

    public List<UsuarioDTO> listarUsuarios(){
        List<UsuarioDTO> resultado = new ArrayList<>();

        List<Usuario> usuarios = usuarioRepository.findAll();
        for(Usuario usuario: usuarios){
            resultado.add(Mapper.mapUsuarioDTO(usuario));
        }
        return resultado;
    }
}
