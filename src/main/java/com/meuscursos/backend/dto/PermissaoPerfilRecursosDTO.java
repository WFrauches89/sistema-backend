package com.meuscursos.backend.dto;


import com.meuscursos.backend.entity.PerfilUsuarioEntity;
import com.meuscursos.backend.entity.PermissaoPerfilRecursoEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class PermissaoPerfilRecursosDTO {


    private Long id;

    private RecursosDTO recursosDTO;

    private PerfilDTO perfilDTO;

    public PermissaoPerfilRecursosDTO(PermissaoPerfilRecursoEntity permissaoPerfilRecursoEntity) {
        BeanUtils.copyProperties(permissaoPerfilRecursoEntity, this);
        if(permissaoPerfilRecursoEntity != null && permissaoPerfilRecursoEntity.getRecursosEntity() != null){
            this.recursosDTO = new RecursosDTO(permissaoPerfilRecursoEntity.getRecursosEntity());
        }
        if(permissaoPerfilRecursoEntity != null && permissaoPerfilRecursoEntity.getPerfilEntity() != null){
            this.perfilDTO = new PerfilDTO(permissaoPerfilRecursoEntity.getPerfilEntity().getId(),permissaoPerfilRecursoEntity.getPerfilEntity().getDescricao());
        }
    }
}
