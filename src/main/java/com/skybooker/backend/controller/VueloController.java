package com.skybooker.backend.controller;

import com.skybooker.backend.model.Vuelo;
import com.skybooker.backend.repository.VueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/vuelos")
@CrossOrigin(origins = "*")
public class VueloController {

    @Autowired
    private VueloRepository vueloRepository;

    // ============================
    // LISTAR TODOS
    // ============================
    @GetMapping
    public List<Vuelo> getAllVuelos() {
        return vueloRepository.findAll();
    }

    // ============================
    // CREAR
    // ============================
    @PostMapping
    public Vuelo crearVuelo(@RequestBody Vuelo vuelo) {
        return vueloRepository.save(vuelo);
    }

    // ============================
    // OBTENER POR ID
    // ============================
    @GetMapping("/{id}")
    public Vuelo getVueloById(@PathVariable Long id) {
        return vueloRepository.findById(id).orElse(null);
    }

    // ============================
    // ACTUALIZAR
    // ============================
    @PutMapping("/{id}")
    public Vuelo actualizarVuelo(@PathVariable Long id, @RequestBody Vuelo data) {
        return vueloRepository.findById(id).map(vuelo -> {

            vuelo.setAerolinea(data.getAerolinea());
            vuelo.setNumeroVuelo(data.getNumeroVuelo());
            vuelo.setOrigen(data.getOrigen());
            vuelo.setDestino(data.getDestino());
            vuelo.setFecha(data.getFecha());                 // java.sql.Date
            vuelo.setHoraSalida(data.getHoraSalida());       // java.sql.Time
            vuelo.setHoraLlegada(data.getHoraLlegada());     // java.sql.Time
            vuelo.setPrecio(data.getPrecio());
            vuelo.setEscalas(data.getEscalas());

            return vueloRepository.save(vuelo);

        }).orElse(null);
    }

    // ============================
    // ELIMINAR
    // ============================
    @DeleteMapping("/{id}")
    public void eliminarVuelo(@PathVariable Long id) {
        vueloRepository.deleteById(id);
    }

    // ============================
    // BUSCAR VUELOS
    // ============================
    @GetMapping("/buscar")
    public List<Vuelo> buscarVuelos(
            @RequestParam String origen,
            @RequestParam String destino,
            @RequestParam String fecha // formato "2025-12-01"
    ) {
        Date fechaSQL = Date.valueOf(fecha);

        return vueloRepository
                .findByOrigenContainingIgnoreCaseAndDestinoContainingIgnoreCaseAndFecha(
                        origen,
                        destino,
                        fechaSQL
                );
    }
}
