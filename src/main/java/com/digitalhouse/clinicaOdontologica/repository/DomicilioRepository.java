package com.digitalhouse.clinicaOdontologica.repository;

import com.digitalhouse.clinicaOdontologica.model.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DomicilioRepository extends JpaRepository<Domicilio, Long> {
    @Modifying
    @Transactional
    @Query("Update Domicilio d set d.calle = ?1, d.numero = ?2, d.localidad = ?3, d.provincia = ?4 where d.id= ?5")
    void actualizarDomicilio(String calle,
                            String numero,
                            String localidad,
                            String provincia,
                            Long id);
}
