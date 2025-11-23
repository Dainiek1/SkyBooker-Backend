package com.skybooker.backend.repository;

import com.skybooker.backend.model.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface VueloRepository extends JpaRepository<Vuelo, Long> {

    List<Vuelo> findByOrigenContainingIgnoreCaseAndDestinoContainingIgnoreCaseAndFecha(
            String origen,
            String destino,
            Date fecha
    );
}
