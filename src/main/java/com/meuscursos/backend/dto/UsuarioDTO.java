package com.meuscursos.backend.dto;


import com.meuscursos.backend.entity.UsuarioEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {


    private Long id;
    private String nome;
    private String sobrenome;
    private String login;
    private String emailAddress;
    private String senha;

    public UsuarioDTO(UsuarioEntity usuarioEntity) {
        BeanUtils.copyProperties(usuarioEntity,this);
    }


}
