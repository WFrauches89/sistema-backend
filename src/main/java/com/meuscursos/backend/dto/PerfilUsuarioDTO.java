package com.meuscursos.backend.dto;

import com.meuscursos.backend.entity.PerfilEntity;
import com.meuscursos.backend.entity.PerfilUsuarioEntity;
import com.meuscursos.backend.entity.RecursosEntity;
import com.meuscursos.backend.entity.UsuarioEntity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class PerfilUsuarioDTO {

    private Long id;

    private UsuarioDTO usuarioDTO;

    private PerfilDTO perfilDTO;

    public PerfilUsuarioDTO(PerfilUsuarioEntity perfilUsuarioEntity) {
        BeanUtils.copyProperties(perfilUsuarioEntity, this);
        if(perfilUsuarioEntity != null && perfilUsuarioEntity.getUsuarioEntity() != null){
            this.usuarioDTO = new UsuarioDTO(perfilUsuarioEntity.getUsuarioEntity());
        }
        if(perfilUsuarioEntity != null && perfilUsuarioEntity.getPerfilEntity() != null){
            this.perfilDTO = new PerfilDTO(perfilUsuarioEntity.getPerfilEntity().getId(),perfilUsuarioEntity.getPerfilEntity().getDescricao());
        }
    }
}
