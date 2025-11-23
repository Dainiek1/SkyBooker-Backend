package com.skybooker.backend.controller;

import com.skybooker.backend.model.Reserva;
import com.skybooker.backend.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "*")
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;

    @PostMapping
    public Reserva crearReserva(@RequestBody Reserva reserva) {
        reserva.setFechaReserva(LocalDateTime.now());
        reserva.setEstado("activa");
        return reservaRepository.save(reserva);
    }

    @GetMapping
    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Reserva> obtenerReservasPorUsuario(@PathVariable Long usuarioId) {
        return reservaRepository.findByUsuarioId(usuarioId);
    }

    @GetMapping("/vuelo/{vueloId}")
    public List<Reserva> obtenerReservasPorVuelo(@PathVariable Long vueloId) {
        return reservaRepository.findByVueloId(vueloId);
    }

    @DeleteMapping("/{id}")
    public void eliminarReserva(@PathVariable Long id) {
        reservaRepository.deleteById(id);
    }
}
