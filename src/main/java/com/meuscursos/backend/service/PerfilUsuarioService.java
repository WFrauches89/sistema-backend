package com.meuscursos.backend.service;

import com.meuscursos.backend.dto.PerfilDTO;
import com.meuscursos.backend.dto.PerfilUsuarioDTO;
import com.meuscursos.backend.entity.PerfilEntity;
import com.meuscursos.backend.entity.PerfilUsuarioEntity;
import com.meuscursos.backend.repository.PerfilRepository;
import com.meuscursos.backend.repository.PerfilUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfilUsuarioService {

    @Autowired
    private PerfilUsuarioRepository repository;


    public List<PerfilUsuarioDTO> getAll(){
        List<PerfilUsuarioEntity> perfilList = repository.findAll();
        return perfilList.stream().map(PerfilUsuarioDTO::new).toList();
    }

    public PerfilUsuarioDTO getPerfilUsuarioById(Long id) {
        return new PerfilUsuarioDTO(repository.findById(id).get());
    }

    public void createPerfilUsuario(PerfilUsuarioDTO perfilDTO){
        PerfilUsuarioEntity novoPerfil = new PerfilUsuarioEntity(perfilDTO);
        repository.save(novoPerfil);
    }

    public PerfilUsuarioDTO updatePerfilUsuario(PerfilUsuarioDTO perfilDTO){
        PerfilUsuarioEntity perfilToUpdate = new PerfilUsuarioEntity(perfilDTO);
        return new PerfilUsuarioDTO(repository.save(perfilToUpdate));
    }

    public void deletePerfilUsuario(Long id){
        repository.delete(repository.getReferenceById(id));
    }


}
