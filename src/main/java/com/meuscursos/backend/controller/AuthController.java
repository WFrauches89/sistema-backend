package com.meuscursos.backend.controller;

import com.meuscursos.backend.dto.Auth.AuthenticationDTO;
import com.meuscursos.backend.dto.UsuarioDTO;
import com.meuscursos.backend.service.Auth.AuthService;
import com.meuscursos.backend.service.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UsuarioService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationDTO authDto){

        return ResponseEntity.ok(authService.login(authDto));
    }

    @PostMapping("/newUser")
    public ResponseEntity<?> newUser(@RequestBody UsuarioDTO usuarioDTO){
        String word = "newUserPublic";
        userService.createUser(usuarioDTO, word);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/verificarUser/{uuid}")
    public String verificarUserEmail(@PathVariable String uuid){
        return userService.verificaUserEmail(uuid);
    }

}
