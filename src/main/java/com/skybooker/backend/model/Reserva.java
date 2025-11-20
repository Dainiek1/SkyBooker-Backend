package com.skybooker.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vuelo_id")
    private Vuelo vuelo;

    @ManyToOne
    @JoinColumn(name = "pasajero_id")
    private Pasajero pasajero;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private String fechaReserva;

    private String estado; // activa o cancelada
}
