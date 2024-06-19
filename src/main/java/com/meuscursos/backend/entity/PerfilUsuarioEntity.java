package com.meuscursos.backend.entity;


import com.meuscursos.backend.dto.PerfilDTO;
import com.meuscursos.backend.dto.PerfilUsuarioDTO;
import com.meuscursos.backend.dto.UsuarioDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.springframework.beans.BeanUtils;


import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "my_perfil_usuario_relacional")
public class PerfilUsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuarioEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_perfil")
    private PerfilEntity perfilEntity;

    public PerfilUsuarioEntity(PerfilUsuarioDTO perfilUsuarioDTO) {
        BeanUtils.copyProperties(perfilUsuarioDTO, this);
        if(perfilUsuarioDTO != null && perfilUsuarioDTO.getUsuarioDTO() != null){
            this.usuarioEntity = new UsuarioEntity(perfilUsuarioDTO.getUsuarioDTO());
        }
        if(perfilUsuarioDTO != null && perfilUsuarioDTO.getPerfilDTO() != null){
            this.perfilEntity = new PerfilEntity(perfilUsuarioDTO.getPerfilDTO());
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof PerfilUsuarioEntity that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
