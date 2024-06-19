package com.meuscursos.backend.entity;

import com.meuscursos.backend.dto.RecursosDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recursos")
public class RecursosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String chave;

    public RecursosEntity(RecursosDTO recursosDTO) {
        BeanUtils.copyProperties(recursosDTO, this);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof RecursosEntity recursos)) return false;
        return Objects.equals(id, recursos.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
