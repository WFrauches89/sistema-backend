package com.meuscursos.backend.entity;

import com.meuscursos.backend.dto.RecursosDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recursos")
public class Recursos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String chave;

    public Recursos(RecursosDTO recursosDTO) {
        BeanUtils.copyProperties(recursosDTO, this);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Recursos recursos)) return false;
        return Objects.equals(id, recursos.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
