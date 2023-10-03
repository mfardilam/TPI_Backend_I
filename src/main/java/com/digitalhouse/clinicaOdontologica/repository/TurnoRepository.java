package com.digitalhouse.clinicaOdontologica.repository;

import com.digitalhouse.clinicaOdontologica.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {

    //@Query("select count(t) from Turnos t where t.odontologo_id= ?1 and t.fecha= ?2")
    @Query(value ="select count(*) from TURNOS WHERE odontologo_id = :id AND fecha = :fecha", nativeQuery = true)
    Long disponibilidadOdontologoFecha(@Param("id") Long id, @Param("fecha") Date fecha);

    /*
    @Query("SELECT COUNT(te) FROM TuEntidad te WHERE te.estado = :estado AND te.fecha > :fecha")
    Long countByEstadoAndFecha(@Param("estado") String estado, @Param("fecha") Date fecha);

    //@Query(value = "SELECT * FROM tu_entidad WHERE estado = :estado", nativeQuery = true)
    //    List<TuEntidad> findByEstado(@Param("estado") String estado);
}*/

}
