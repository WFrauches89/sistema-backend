package com.meuscursos.backend.controller;

import com.meuscursos.backend.dto.PerfilUsuarioDTO;
import com.meuscursos.backend.dto.PermissaoPerfilRecursosDTO;
import com.meuscursos.backend.service.PerfilUsuarioService;
import com.meuscursos.backend.service.PermissaoPerfilRecursosService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissao-perfil-recurso")
public class PermissaoPerfilUsuarioController {

    @Autowired
    private PermissaoPerfilRecursosService service;

    @GetMapping
    public List<PermissaoPerfilRecursosDTO> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public PermissaoPerfilRecursosDTO getPermissaoPerfilRecursosById(@PathVariable Long id) {
        return service.getPermissaoPerfilRecursosById(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Object> createPermissaoPerfilRecursos(@RequestBody PermissaoPerfilRecursosDTO permissaoPerfilRecursosDTO){
        service.createPermissaoPerfilRecursos(permissaoPerfilRecursosDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public PermissaoPerfilRecursosDTO updatePermissaoPerfilRecursos(@PathVariable Long id, @RequestBody PermissaoPerfilRecursosDTO permissaoPerfilRecursosDTO){
        return service.updatePermissaoPerfilRecursos(permissaoPerfilRecursosDTO);
    }



    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletePermissaoPerfilRecursos(@PathVariable Long id){
        service.deletePermissaoPerfilRecursos(id);
        return ResponseEntity.noContent().build();
    }
}
