package com.skybooker.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

@Data
@Entity
@Table(name = "vuelos")
public class Vuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String aerolinea;

    @Column(name="numero_vuelo")
    private String numeroVuelo;

    private String origen;
    private String destino;

    private Date fecha; // java.sql.Date

    @Column(name="hora_salida")
    private Time horaSalida;

    @Column(name="hora_llegada")
    private Time horaLlegada;

    private Integer precio;

    private Integer escalas = 0;
}
