package com.skybooker.backend.controller;

import com.skybooker.backend.model.Pasajero;
import com.skybooker.backend.repository.PasajeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pasajeros")
@CrossOrigin(origins = "*")
public class PasajeroController {

    @Autowired
    private PasajeroRepository pasajeroRepository;

    // CREAR
    @PostMapping
    public Pasajero crearPasajero(@RequestBody Pasajero pasajero) {
        return pasajeroRepository.save(pasajero);
    }

    // LISTAR
    @GetMapping
    public List<Pasajero> listarPasajeros() {
        return pasajeroRepository.findAll();
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public Pasajero obtenerPasajero(@PathVariable Long id) {
        return pasajeroRepository.findById(id).orElse(null);
    }

    // ACTUALIZAR
    @PutMapping("/{id}")
    public Pasajero actualizarPasajero(
            @PathVariable Long id,
            @RequestBody Pasajero p
    ) {
        Pasajero db = pasajeroRepository.findById(id).orElse(null);
        if (db == null) return null;

        db.setNombre(p.getNombre());
        db.setDocumento(p.getDocumento());
        db.setEmail(p.getEmail());

        return pasajeroRepository.save(db);
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public String eliminarPasajero(@PathVariable Long id) {
        pasajeroRepository.deleteById(id);
        return "Pasajero eliminado con éxito";
    }
}
