package com.meuscursos.backend.entity;


import com.meuscursos.backend.dto.PermissaoPerfilRecursosDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "my_permissao_perfil_recurso_relacional")
public class PermissaoPerfilRecursoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_recursos")
    private RecursosEntity recursosEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_perfil")
    private PerfilEntity perfilEntity;

    public PermissaoPerfilRecursoEntity(PermissaoPerfilRecursosDTO permissaoPerfilRecursosDTO) {
        BeanUtils.copyProperties(permissaoPerfilRecursosDTO, this);
        if(permissaoPerfilRecursosDTO != null && permissaoPerfilRecursosDTO.getRecursosDTO() != null){
            this.recursosEntity = new RecursosEntity(permissaoPerfilRecursosDTO.getRecursosDTO());
        }
        if(permissaoPerfilRecursosDTO != null && permissaoPerfilRecursosDTO.getPerfilDTO() != null){
            this.perfilEntity = new PerfilEntity(permissaoPerfilRecursosDTO.getPerfilDTO());
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof PermissaoPerfilRecursoEntity that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
