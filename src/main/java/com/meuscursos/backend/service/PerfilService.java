package com.meuscursos.backend.service;

import com.meuscursos.backend.dto.PerfilDTO;
import com.meuscursos.backend.dto.RecursosDTO;
import com.meuscursos.backend.entity.PerfilEntity;
import com.meuscursos.backend.entity.RecursosEntity;
import com.meuscursos.backend.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;


    public List<PerfilDTO> getAll(){
        List<PerfilEntity> perfilList = perfilRepository.findAll();
        return perfilList.stream().map(perfilEntity -> new PerfilDTO(perfilEntity.getId(), perfilEntity.getDescricao())).toList();
    }

    public PerfilDTO getPerfilById(Long id) {
        var perfilEntity = perfilRepository.findById(id).get();
        return new PerfilDTO(perfilEntity.getId(),perfilEntity.getDescricao());
    }

    public void createPerfil(PerfilDTO perfilDTO){
        PerfilEntity novoPerfil = new PerfilEntity(perfilDTO);
        perfilRepository.save(novoPerfil);
    }

    public PerfilDTO updatePerfil(PerfilDTO perfilDTO){
        PerfilEntity perfilToUpdate = new PerfilEntity(perfilDTO);
        PerfilEntity saved = perfilRepository.save(perfilToUpdate);
        return new PerfilDTO(saved.getId(), saved.getDescricao());
    }

    public void deletePerfil(Long id){
        perfilRepository.delete(perfilRepository.getReferenceById(id));
    }


}
