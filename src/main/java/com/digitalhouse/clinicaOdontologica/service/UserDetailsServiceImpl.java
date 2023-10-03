package com.digitalhouse.clinicaOdontologica.service;

import com.digitalhouse.clinicaOdontologica.model.Usuario;
import com.digitalhouse.clinicaOdontologica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService{

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    @Autowired
    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Buscar usuario de DB

        Usuario usuario = usuarioRepository.findUsuarioByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario  no encontrado"));

        //Lista de Permisos/Roles. En NUESTRO caso solo tienen un ROL.
        Set<GrantedAuthority> grantList = new HashSet<>();
        //aca le pasamos el rol
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(usuario.getRol().toString());
        grantList.add(grantedAuthority);


        //Devuelvo un usuario de Spring
        UserDetails user = new User(usuario.getUsername(), usuario.getPassword(), grantList);

        return user;
    }
}
