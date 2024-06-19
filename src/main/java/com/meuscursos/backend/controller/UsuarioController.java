package com.meuscursos.backend.controller;

import com.meuscursos.backend.dto.UsuarioDTO;
import com.meuscursos.backend.service.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioDTO> getAllUsers(){
        return usuarioService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UsuarioDTO getUserById(@PathVariable Long id){
        return usuarioService.getUserByID(id);
    }

    @Transactional
    @PostMapping
    public void createUser(@RequestBody UsuarioDTO usuarioDTO){
        usuarioService.createUser(usuarioDTO);
    }

    @Transactional
    @PutMapping("/{id}")
    public UsuarioDTO updateUsers(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDto){
       return usuarioService.updateUsers(usuarioDto);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        usuarioService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


}
