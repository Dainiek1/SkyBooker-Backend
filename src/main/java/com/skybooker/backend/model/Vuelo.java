package com.skybooker.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "vuelos")
public class Vuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String aerolinea;
    private String numeroVuelo;
    private String origen;
    private String destino;

    private String fecha;    // YYYY-MM-DD
    private String horaSalida;
    private String horaLlegada;

    private String duracion;

    private Double precio;

    private Integer escalas;
    private Integer cuposDisponibles;
}
