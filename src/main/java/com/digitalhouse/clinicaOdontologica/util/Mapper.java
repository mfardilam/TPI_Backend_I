package com.digitalhouse.clinicaOdontologica.util;

import com.digitalhouse.clinicaOdontologica.dto.DomicilioDTO;
import com.digitalhouse.clinicaOdontologica.dto.PacienteDTO;
import com.digitalhouse.clinicaOdontologica.dto.TurnoDTO;
import com.digitalhouse.clinicaOdontologica.dto.UsuarioDTO;
import com.digitalhouse.clinicaOdontologica.model.Domicilio;
import com.digitalhouse.clinicaOdontologica.model.Paciente;
import com.digitalhouse.clinicaOdontologica.model.Turno;
import com.digitalhouse.clinicaOdontologica.model.Usuario;

public class Mapper {

        public static PacienteDTO mapPacienteDTO(Paciente paciente){
            PacienteDTO pacienteDTO = new PacienteDTO();

            pacienteDTO.setId(paciente.getId());
            pacienteDTO.setNombre(paciente.getNombre());
            pacienteDTO.setApellido(paciente.getApellido());
            pacienteDTO.setDni(paciente.getDni());
            pacienteDTO.setFechaAlta(paciente.getFechaAlta());
            pacienteDTO.setDomicilio_id(paciente.getDomicilio().getId());

            return pacienteDTO;
        }

        public static TurnoDTO mapTurnoDTO(Turno turno){
            TurnoDTO turnoDTO = new TurnoDTO();

            turnoDTO.setId(turno.getId());
            turnoDTO.setFecha(turno.getFecha());
            turnoDTO.setOdontologo_id(turno.getOdontologo().getId());
            turnoDTO.setPaciente_id(turno.getPaciente().getId());

            return turnoDTO;
        }

        public static UsuarioDTO mapUsuarioDTO(Usuario usuario){
            UsuarioDTO usuarioDTO = new UsuarioDTO();

            usuarioDTO.setId(usuario.getId());
            usuarioDTO.setUsername(usuario.getUsername());
            usuarioDTO.setPassword(usuario.getPassword());
            usuarioDTO.setRol(usuario.getRol());

            return usuarioDTO;
        }



}
