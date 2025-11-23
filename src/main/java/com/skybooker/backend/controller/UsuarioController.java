package com.skybooker.backend.controller;

import com.skybooker.backend.model.Usuario;
import com.skybooker.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario obtener(@PathVariable Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Usuario crear(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @PutMapping("/{id}")
    public Usuario actualizar(@PathVariable Long id, @RequestBody Usuario nuevo) {
        return usuarioRepository.findById(id)
                .map(u -> {
                    u.setNombre(nuevo.getNombre());
                    u.setEmail(nuevo.getEmail());
                    u.setPasswordHash(nuevo.getPasswordHash());
                    u.setRol(nuevo.getRol());
                    return usuarioRepository.save(u);
                }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }
}
