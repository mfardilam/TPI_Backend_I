package com.digitalhouse.clinicaOdontologica.service;
import com.digitalhouse.clinicaOdontologica.dto.*;
import com.digitalhouse.clinicaOdontologica.model.Odontologo;
import com.digitalhouse.clinicaOdontologica.model.Paciente;
import com.digitalhouse.clinicaOdontologica.model.Turno;
import com.digitalhouse.clinicaOdontologica.repository.TurnoRepository;
import com.digitalhouse.clinicaOdontologica.util.exception.BadRequestException;
import com.digitalhouse.clinicaOdontologica.util.exception.ResoruceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class TurnoServiceTest {
    @Mock
    private OdontologoService odontologoService;
    @Mock
    private PacienteService pacienteService;
    private TurnoService turnoService;
    @Mock
    private TurnoRepository turnoRepository;
    @Mock
    private ObjectMapper objectMapper;

    @BeforeEach
    public void configuiracion(){
        initMocks(this);
        turnoService = new TurnoService(turnoRepository,pacienteService,odontologoService,objectMapper);
    }


    @Test
    public void dadoUnOdontologoSiNoExisteCuandoLLamoAlMetodoCrearTurnoEntoncesDevuelveUnasExcepcion(){
        TurnoDTO turnoDTO = mock(TurnoDTO.class);
        when(turnoDTO.getOdontologo_id()).thenReturn(1L);
        when(odontologoService.existe(any())).thenReturn(false);
        BadRequestException badRequestException = assertThrows(BadRequestException.class, () -> turnoService.crearTurno(turnoDTO));
        assertEquals("El odontólogo con el que desea agendar no exite",badRequestException.getMessage());

    }
    @Test
    public void dadoUnPacienteSiNoExisteCuandoLLamoAlMetodoCrearTurnoEntoncesDevuelveUnasExcepcion(){
        TurnoDTO turnoDTO = mock(TurnoDTO.class);
        when(turnoDTO.getOdontologo_id()).thenReturn(1L);
        when(turnoDTO.getPaciente_id()).thenReturn(1L);
        when(odontologoService.existe(any())).thenReturn(true);
        when(pacienteService.existe(any())).thenReturn(false);
        BadRequestException badRequestException = assertThrows(BadRequestException.class, () -> turnoService.crearTurno(turnoDTO));
        assertEquals("El paciente con el que desea agendar no existe",badRequestException.getMessage());

    }

    @Test
    public void dadoUnaFechaOcupadaSinDisponibilidadCuandoLLamoAlMetodoCrearTurnoEntoncesDevuelveUnasExcepcion(){
        TurnoDTO turnoDTO = mock(TurnoDTO.class);
        when(turnoDTO.getOdontologo_id()).thenReturn(1L);
        when(turnoDTO.getPaciente_id()).thenReturn(1L);
        when(odontologoService.existe(any())).thenReturn(true);
        when(pacienteService.existe(any())).thenReturn(true);
        when(turnoDTO.getFecha()).thenReturn(new Date());
        when(turnoRepository.disponibilidadOdontologoFecha(any(),any())).thenReturn(1L);
        BadRequestException badRequestException = assertThrows(BadRequestException.class, () -> turnoService.crearTurno(turnoDTO));
        assertEquals("El odontólogo ya cuenta con un turno para la fecha ingresada",badRequestException.getMessage());
    }

    @Test
    public void cuandoLLamoCrearTurnoDevuelveOk(){
        TurnoDTO turnoDTO = mock(TurnoDTO.class);
        when(turnoDTO.getOdontologo_id()).thenReturn(1L);
        when(turnoDTO.getPaciente_id()).thenReturn(1L);
        when(odontologoService.existe(any())).thenReturn(true);
        when(pacienteService.existe(any())).thenReturn(true);
        when(turnoDTO.getFecha()).thenReturn(new Date());
        when(turnoRepository.disponibilidadOdontologoFecha(any(),any())).thenReturn(0L);
        when(odontologoService.buscarOdontologo(any())).thenReturn(mock(OdontologoDTO.class));
        when(pacienteService.buscarPaciente(any())).thenReturn(mock(PacienteDTO.class));
        when(objectMapper.convertValue(any(OdontologoDTO.class),eq(Odontologo.class))).thenReturn(mock(Odontologo.class));
        when(objectMapper.convertValue(any(PacienteDTO.class),eq(Paciente.class))).thenReturn(mock(Paciente.class));

        Turno turno = turnoService.crearTurno(turnoDTO);
        assertNotNull(turno);
    }

    @Test
    public void cuandoLLamoListarDevuelveUnaListaVacia(){
        when(turnoRepository.findAll()).thenReturn(new ArrayList());
        List<TurnoDTO> turnoDTOS = turnoService.listarTurnos();
        assertTrue(turnoDTOS.isEmpty());
    }

    @Test
    public void cuandoLLamoListarDevuelveUnaLista(){
        ArrayList<Turno> turnos = new ArrayList<>();
        turnos.add(getTurno());
        when(turnoRepository.findAll()).thenReturn(turnos);
        List<TurnoDTO> turnoDTOS = turnoService.listarTurnos();
        assertFalse(turnoDTOS.isEmpty());
        assertEquals(1L, turnos.get(0).getId());
    }
    private Turno getTurno(){
        Turno turno = mock(Turno.class);
        when(turno.getId()).thenReturn(1L);
        when(turno.getFecha()).thenReturn(new Date());
        Odontologo odontologo = mock(Odontologo.class);
        when(odontologo.getId()).thenReturn(1L);
        when(turno.getOdontologo()).thenReturn(odontologo);
        Paciente paciente = mock(Paciente.class);
        when(paciente.getId()).thenReturn(1L);
        when(turno.getPaciente()).thenReturn(paciente);

        return turno;
    }

    @Test
    public void cuandoLLamoBuscarUnTurnoInexistenteDevuleveUnaExcepcion(){
        when(turnoRepository.existsById(any())).thenReturn(false);
        ResoruceNotFoundException resoruceNotFoundException = assertThrows(ResoruceNotFoundException.class, () -> turnoService.buscarTurno(1L));
    }


    @Test
    public void cuandoLLamoBorrarUnTurnoInexistenteDevuleveUnaExcepcion(){
        when(turnoRepository.existsById(any())).thenReturn(false);
        ResoruceNotFoundException resoruceNotFoundException = assertThrows(ResoruceNotFoundException.class, () -> turnoService.borrarTurno(1L));
    }

    @Test
    public void cuandoLLamoBorrarDevuelveUnTurno(){
        when(turnoRepository.existsById(any())).thenReturn(true);
        turnoService.borrarTurno(1L);
    }
}
