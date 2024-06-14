package com.meuscursos.backend.service;

import com.meuscursos.backend.dto.UsuarioDTO;
import com.meuscursos.backend.entity.UsuarioEntity;
import com.meuscursos.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioDTO> getAllUsers(){
        List<UsuarioEntity> listaUsuariosEntity = usuarioRepository.findAll();
        return listaUsuariosEntity.stream().map(UsuarioDTO::new).toList();
    }
    public UsuarioDTO getUserByID(Long id){
        return new UsuarioDTO(usuarioRepository.getReferenceById(id));

    }

    public void createUser(UsuarioDTO usuarioDTO){
        UsuarioEntity usuario = new UsuarioEntity(usuarioDTO);
        usuarioRepository.save(usuario);
    }

    public UsuarioDTO updateUsers(UsuarioDTO usuarioDto){
        UsuarioEntity usuario = new UsuarioEntity(usuarioDto);
        return new UsuarioDTO(usuarioRepository.save(usuario));

    }

    public void deleteUser(Long id){
        usuarioRepository.delete(usuarioRepository.getReferenceById(id));
    }

}
