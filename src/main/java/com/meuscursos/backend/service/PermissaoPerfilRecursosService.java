package com.meuscursos.backend.service;

import com.meuscursos.backend.dto.PerfilUsuarioDTO;
import com.meuscursos.backend.dto.PermissaoPerfilRecursosDTO;
import com.meuscursos.backend.entity.PerfilUsuarioEntity;
import com.meuscursos.backend.entity.PermissaoPerfilRecursoEntity;
import com.meuscursos.backend.repository.PermissaoPerfilRecursosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissaoPerfilRecursosService {

    @Autowired
    private PermissaoPerfilRecursosRepository repository;


    public List<PermissaoPerfilRecursosDTO> getAll(){
        List<PermissaoPerfilRecursoEntity> permissaoList = repository.findAll();
        return permissaoList.stream().map(PermissaoPerfilRecursosDTO::new).toList();
    }

    public PermissaoPerfilRecursosDTO getPermissaoPerfilRecursosById(Long id) {
        return new PermissaoPerfilRecursosDTO(repository.findById(id).get());
    }

    public void createPermissaoPerfilRecursos(PermissaoPerfilRecursosDTO permissaoPerfilRecursosDTO){
        PermissaoPerfilRecursoEntity novoPerfil = new PermissaoPerfilRecursoEntity(permissaoPerfilRecursosDTO);
        repository.save(novoPerfil);
    }

    public PermissaoPerfilRecursosDTO updatePermissaoPerfilRecursos(PermissaoPerfilRecursosDTO permissaoPerfilRecursosDTO){
        PermissaoPerfilRecursoEntity permissaoPerfilRecursosToUpdate = new PermissaoPerfilRecursoEntity(permissaoPerfilRecursosDTO);
        return new PermissaoPerfilRecursosDTO(repository.save(permissaoPerfilRecursosToUpdate));
    }

    public void deletePermissaoPerfilRecursos(Long id){
        repository.delete(repository.getReferenceById(id));
    }


}
