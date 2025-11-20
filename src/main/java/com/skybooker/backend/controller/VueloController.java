package com.skybooker.backend.controller;

import com.skybooker.backend.model.Vuelo;
import com.skybooker.backend.repository.VueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vuelos")
@CrossOrigin(origins = "*")
public class VueloController {

    @Autowired
    private VueloRepository vueloRepository;

    // GET: Listar todos los vuelos
    @GetMapping
    public List<Vuelo> getAllVuelos() {
        return vueloRepository.findAll();
    }

    // GET: Buscar vuelo por ID
    @GetMapping("/{id}")
    public Vuelo getVueloById(@PathVariable Long id) {
        return vueloRepository.findById(id).orElse(null);
    }

    // GET: Buscar vuelos por filtros
    @GetMapping("/buscar")
    public List<Vuelo> buscarVuelos(
            @RequestParam String origen,
            @RequestParam String destino,
            @RequestParam String fecha
    ) {
        return vueloRepository.findByOrigenContainingIgnoreCaseAndDestinoContainingIgnoreCaseAndFecha(
                origen, destino, fecha
        );
    }
}
