package com.meuscursos.backend.controller;

import com.meuscursos.backend.dto.PerfilUsuarioDTO;
import com.meuscursos.backend.service.PerfilUsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfil-usuario")
public class PerfilUsuarioController {

    @Autowired
    private PerfilUsuarioService service;

    @GetMapping
    public List<PerfilUsuarioDTO> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public PerfilUsuarioDTO getPerfilUsuarioById(@PathVariable Long id) {
        return service.getPerfilUsuarioById(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Object> createPerfilUsuario(@RequestBody PerfilUsuarioDTO perfilDTO){
        service.createPerfilUsuario(perfilDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public PerfilUsuarioDTO updatePerfilUsuario(@PathVariable Long id, @RequestBody PerfilUsuarioDTO perfilDTO){
        return service.updatePerfilUsuario(perfilDTO);
    }



    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletePerfilUsuario(@PathVariable Long id){
        service.deletePerfilUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
