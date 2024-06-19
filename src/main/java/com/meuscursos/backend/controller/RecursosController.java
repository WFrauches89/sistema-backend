package com.meuscursos.backend.controller;

import com.meuscursos.backend.dto.RecursosDTO;
import com.meuscursos.backend.dto.UsuarioDTO;
import com.meuscursos.backend.entity.Recursos;
import com.meuscursos.backend.repository.RecursosRepository;
import com.meuscursos.backend.service.RecursosService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recursos")
public class RecursosController {

    @Autowired
    private RecursosService recursosService;

    @GetMapping
    public List<RecursosDTO> getAll(){
        return recursosService.getAll();
    }
    @GetMapping("/{id}")
    public RecursosDTO getRecursoById(@PathVariable Long id) {
        return recursosService.getRecursoById(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> createRecurso(@RequestBody RecursosDTO recursosDTO){
        recursosService.createRecurso(recursosDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public RecursosDTO updateRecurso(@PathVariable Long id, @RequestBody RecursosDTO recursosDTO){
        return recursosService.updateRecurso(recursosDTO);
    }



    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteRecurso(@PathVariable Long id){
        recursosService.deleteRecurso(id);
        return ResponseEntity.noContent().build();
    }





}
