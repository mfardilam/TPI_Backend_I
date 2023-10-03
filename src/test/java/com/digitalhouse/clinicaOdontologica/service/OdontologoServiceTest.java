package com.digitalhouse.clinicaOdontologica.service;

import com.digitalhouse.clinicaOdontologica.dto.CrearOdontologoDTO;
import com.digitalhouse.clinicaOdontologica.dto.OdontologoDTO;
import com.digitalhouse.clinicaOdontologica.model.Odontologo;
import com.digitalhouse.clinicaOdontologica.util.exception.ResoruceNotFoundException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class OdontologoServiceTest {
    private final OdontologoService odontologoService;
    @Autowired
    public OdontologoServiceTest(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @Test
    @Order(1)
    public void crearOdontologo() {
        CrearOdontologoDTO odontologoDTO = new CrearOdontologoDTO("Juan","Gonzalez","AC1672");
        Odontologo respuesta = odontologoService.crearOdontologo(odontologoDTO);

        assertEquals(1L, respuesta.getId());
    }
    @Test
    @Order(2)
    public void buscarOdontologoExistente(){
        Long id = 1L;
        OdontologoDTO respuesta = odontologoService.buscarOdontologo(id);

        assertNotNull(respuesta);
        assertTrue(odontologoService.existe(id));
    }

    @Test
    @Order(3)
    public void buscarOdontologoNoExistente(){
        Long id = 5L;

        assertThrows(ResoruceNotFoundException.class, ()->odontologoService.buscarOdontologo(id));
        assertFalse(odontologoService.existe(id));
    }

    @Test
    @Order(4)
    public void listarOdontologos(){
        Integer tamanioEsperado = 1;
        List<OdontologoDTO> resultado = odontologoService.listarOdontologos();

        assertEquals(tamanioEsperado, resultado.size());
    }

    @Test
    @Order(5)
    public void actualizarOdontologoExistente(){
        OdontologoDTO odontologoDTO = new OdontologoDTO(1L,"Juanito","Gonza","A1672");
        odontologoService.actualizarOdontologo(odontologoDTO);
        OdontologoDTO entidadActualizada = odontologoService.buscarOdontologo(1L);

        assertEquals("Juanito", entidadActualizada.getNombre());
        assertEquals("Gonza", entidadActualizada.getApellido());
        assertEquals("A1672", entidadActualizada.getMatricula());
    }

    @Test
    @Order(6)
    public void actualizarOdontologoNoExistente(){
        OdontologoDTO odontologoDTO = new OdontologoDTO(7L,"Juanito","Gonza","A1672");

        assertThrows(ResoruceNotFoundException.class, ()->odontologoService.actualizarOdontologo(odontologoDTO));
    }

    @Test
    @Order(7)
    public void borrarOdontologoExistente(){
        Long id = 1L;
        odontologoService.borrarOdontologo(id);

        assertThrows(ResoruceNotFoundException.class, ()->odontologoService.buscarOdontologo(id));

    }

    @Test
    @Order(8)
    public void borrarOdontologoNoExistente(){
        Long id = 6L;

        assertThrows(ResoruceNotFoundException.class, ()->odontologoService.borrarOdontologo(id));

    }


}
