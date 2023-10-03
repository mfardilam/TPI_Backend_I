package com.digitalhouse.clinicaOdontologica.security;

import com.digitalhouse.clinicaOdontologica.dto.UsuarioDTO;
import com.digitalhouse.clinicaOdontologica.model.Rol;
import com.digitalhouse.clinicaOdontologica.model.Usuario;
import com.digitalhouse.clinicaOdontologica.repository.UsuarioRepository;
import com.digitalhouse.clinicaOdontologica.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CargaInicialUsuarios implements ApplicationRunner {

    private final UsuarioService service;
    @Autowired
    public CargaInicialUsuarios(UsuarioService service) {
        this.service = service;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //Genera el password encriptado
        String passwordAdmin = passwordEncoder.encode("valen1234");
        String passwordUser = passwordEncoder.encode("fulano1234");


        service.crearUsuario(new UsuarioDTO("valeria", passwordAdmin, Rol.ROLE_ADMIN));
        service.crearUsuario(new UsuarioDTO("fulano", passwordUser, Rol.ROLE_USER));

    }
}
