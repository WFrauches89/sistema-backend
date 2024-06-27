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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationDTO authDto){
        System.out.println("authDTO username: "+authDto.getUsername()+"authDTO senha: "+authDto.getPassword() );
        return ResponseEntity.ok(authService.login(authDto));
    }




}
