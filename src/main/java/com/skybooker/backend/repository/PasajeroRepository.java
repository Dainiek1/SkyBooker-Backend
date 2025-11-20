package com.skybooker.backend.repository;

import com.skybooker.backend.model.Pasajero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasajeroRepository extends JpaRepository<Pasajero, Long> {
}
