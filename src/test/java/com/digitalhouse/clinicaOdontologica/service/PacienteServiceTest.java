package com.digitalhouse.clinicaOdontologica.service;


import com.digitalhouse.clinicaOdontologica.dto.*;
import com.digitalhouse.clinicaOdontologica.model.Paciente;
import com.digitalhouse.clinicaOdontologica.util.exception.ResoruceNotFoundException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class PacienteServiceTest {
    private final PacienteService pacienteService;
    private final DomicilioService domicilioService;
    @Autowired
    public PacienteServiceTest(PacienteService pacienteService, DomicilioService domicilioService) {
        this.pacienteService = pacienteService;
        this.domicilioService = domicilioService;
    }
    @Test
    @Order(1)
    public void crearPaciente() {
        CrearDomicilioDTO domicilioDTO = new CrearDomicilioDTO("Cabello", "3107", "BA", "BA");

        long miliseconds = System.currentTimeMillis();
        Date fechaAlta = new Date(miliseconds);
        CrearPacienteDTO pacienteDTO = new CrearPacienteDTO("Rosa","Jimenez","12345678",
                fechaAlta, domicilioDTO);
        Paciente respuesta = pacienteService.crearPaciente(pacienteDTO);

        assertEquals(1L, respuesta.getId());
    }
    @Test
    @Order(2)
    public void buscarPacienteExistente(){
        Long id = 1L;
        PacienteDTO respuesta = pacienteService.buscarPaciente(id);

        assertNotNull(respuesta);
        assertTrue(pacienteService.existe(id));
    }

    @Test
    @Order(3)
    public void buscarPacienteNoExistente(){
        Long id = 5L;

        assertThrows(ResoruceNotFoundException.class, ()->pacienteService.buscarPaciente(id));
        assertFalse(pacienteService.existe(id));
    }

    @Test
    @Order(4)
    public void listarPacientes(){
        Integer tamanioEsperado = 1;
        List<PacienteDTO> resultado = pacienteService.listarPacientes();

        assertEquals(tamanioEsperado, resultado.size());
    }

    @Test
    @Order(5)
    public void actualizarPacienteExistente(){
        long miliseconds = System.currentTimeMillis();
        Date fechaAlta = new Date(miliseconds);
        ActualizarPacienteDTO pacienteDTO = new ActualizarPacienteDTO(1l,"Rosita","Jim","89456",
                fechaAlta, new DomicilioDTO(1L,"Palermo", "10a", "La Plata", "BA"));
        pacienteService.actualizarPaciente(pacienteDTO);
        PacienteDTO entidadActualizada = pacienteService.buscarPaciente(1L);
        DomicilioDTO domicilioAsociado = domicilioService.buscarDomicilio(1L);

        assertEquals("Rosita", entidadActualizada.getNombre());
        assertEquals("Palermo", domicilioAsociado.getCalle());
    }

    @Test
    @Order(6)
    public void actualizarPacienteNoExistente(){
        long miliseconds = System.currentTimeMillis();
        Date fechaAlta = new Date(miliseconds);
        ActualizarPacienteDTO pacienteDTO = new ActualizarPacienteDTO(8l,"Rosita","Jim","89456",
                fechaAlta, new DomicilioDTO(9L,"Palermo", "10a", "La Plata", "BA"));

        assertThrows(ResoruceNotFoundException.class, ()->pacienteService.actualizarPaciente(pacienteDTO));
    }

    @Test
    @Order(7)
    public void borrarPacienteExistente(){
        Long id = 1L;
        pacienteService.borrarPaciente(id);

        assertThrows(ResoruceNotFoundException.class, ()->pacienteService.borrarPaciente(id));

    }

    @Test
    @Order(8)
    public void borrarPacienteNoExistente(){
        Long id = 6L;

        assertThrows(ResoruceNotFoundException.class, ()->pacienteService.borrarPaciente(id));

    }
}
