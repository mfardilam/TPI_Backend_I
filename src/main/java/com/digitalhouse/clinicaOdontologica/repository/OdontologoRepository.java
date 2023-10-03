package com.digitalhouse.clinicaOdontologica.repository;

import com.digitalhouse.clinicaOdontologica.model.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {

    @Modifying
    @Transactional
    @Query("Update Odontologo o set o.nombre = ?1, o.apellido = ?2, o.matricula = ?3 where o.id= ?4")
    void actualizarOdontologo(String nombre, String apellido, String matricula, Long id);


}
