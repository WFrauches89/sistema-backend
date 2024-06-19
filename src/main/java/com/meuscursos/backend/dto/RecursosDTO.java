package com.meuscursos.backend.dto;

import com.meuscursos.backend.entity.RecursosEntity;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class RecursosDTO {

    private Long id;
    private String nome;
    private String chave;

    public RecursosDTO(RecursosEntity recursos) {
        BeanUtils.copyProperties(recursos, this);
    }

}
