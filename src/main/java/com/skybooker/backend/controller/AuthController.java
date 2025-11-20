package com.skybooker.backend.controller;

import com.skybooker.backend.model.Usuario;
import com.skybooker.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public Object login(@RequestBody Usuario datosLogin) {

        // 1. Buscar usuario por email
        Usuario usuario = usuarioRepository.findByEmail(datosLogin.getEmail())
                .orElse(null);

        if (usuario == null) {
            return "Usuario no encontrado";
        }

        // 2. Validar contraseña
        boolean passwordOk = BCrypt.checkpw(
                datosLogin.getPasswordHash(),
                usuario.getPasswordHash()
        );

        if (!passwordOk) {
            return "Contraseña incorrecta";
        }

        // 3. Respuesta de login
        return new Object() {
            public final Long id = usuario.getId();
            public final String rol = usuario.getRol();
            public final String nombre = usuario.getNombre();
        };
    }
}
