package com.meuscursos.backend.entity;


import com.meuscursos.backend.dto.UsuarioDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "my_users")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String sobrenome;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false, unique = true)
    private String senha;

    @Column(nullable = false, unique = true)
    private String email;

    public UsuarioEntity(UsuarioDTO usuarioEntityDTO) {
        BeanUtils.copyProperties(usuarioEntityDTO, this);
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof UsuarioEntity that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}
