package com.meuscursos.backend.controller;

import com.meuscursos.backend.dto.PerfilDTO;
import com.meuscursos.backend.dto.RecursosDTO;
import com.meuscursos.backend.service.PerfilService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfis")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @GetMapping
    public List<PerfilDTO> getAll(){
        return perfilService.getAll();
    }
    @GetMapping("/{id}")
    public PerfilDTO getPerfilById(@PathVariable Long id) {
        return perfilService.getPerfilById(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Object> createPerfil(@RequestBody PerfilDTO perfilDTO){
        perfilService.createPerfil(perfilDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public PerfilDTO updatePerfil(@PathVariable Long id, @RequestBody PerfilDTO perfilDTO){
        return perfilService.updatePerfil(perfilDTO);
    }



    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletePerfil(@PathVariable Long id){
        perfilService.deletePerfil(id);
        return ResponseEntity.noContent().build();
    }
}
