package com.meuscursos.backend.service;

import com.meuscursos.backend.dto.RecursosDTO;
import com.meuscursos.backend.entity.Recursos;
import com.meuscursos.backend.repository.RecursosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecursosService {

    @Autowired
    private RecursosRepository repository;

    public List<RecursosDTO> getAll(){
        List<Recursos> recursosList = repository.findAll();
        return recursosList.stream().map(RecursosDTO::new).toList();
    }

    public RecursosDTO getRecursoById(Long id) {
        return new RecursosDTO(repository.getReferenceById(id));
    }

    public void createRecurso(RecursosDTO recursosDTO){
        Recursos novoRecurso = new Recursos(recursosDTO);
        repository.save(novoRecurso);
    }

    public RecursosDTO updateRecurso(RecursosDTO recursosDTO){
        Recursos recursoToUpdate = new Recursos(recursosDTO);
        return new RecursosDTO(repository.save(recursoToUpdate));
    }

    public void deleteRecurso(Long id){
        repository.delete(repository.getReferenceById(id));
    }



}
