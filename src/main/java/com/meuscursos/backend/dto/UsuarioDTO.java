package com.meuscursos.backend.dto;


import com.meuscursos.backend.entity.UserStatusEnumType;
import com.meuscursos.backend.entity.UsuarioEntity;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {


    private Long id;
    private String nome;
    private String sobrenome;
    private String login;
    private String senha;
    private String email;
    private UserStatusEnumType status;

    public UsuarioDTO(UsuarioEntity usuarioEntity) {
        BeanUtils.copyProperties(usuarioEntity,this);
    }


}
