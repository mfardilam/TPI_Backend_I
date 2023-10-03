package com.digitalhouse.clinicaOdontologica.repository;

import com.digitalhouse.clinicaOdontologica.dto.DomicilioDTO;
import com.digitalhouse.clinicaOdontologica.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    @Modifying
    @Transactional
    @Query("Update Paciente p set p.nombre = ?1, p.apellido = ?2, p.dni = ?3, p.fechaAlta = ?4 where p.id= ?5")
    void actualizarPaciente(String nombre,
                            String apellido,
                            String dni,
                            Date fechaAlta,
                            Long id);
}
