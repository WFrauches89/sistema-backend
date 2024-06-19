package com.meuscursos.backend.dto;

import com.meuscursos.backend.entity.Recursos;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

@Data
@NoArgsConstructor
public class RecursosDTO {

    private Long id;
    private String nome;
    private String chave;

    public RecursosDTO(Recursos recursos) {
        BeanUtils.copyProperties(recursos, this);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof RecursosDTO that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
