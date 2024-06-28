package com.meuscursos.backend.entity.email;

import com.meuscursos.backend.entity.UsuarioEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class VerificadorEmailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Instant expiration;

    @Column(nullable = false)
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "id_usuario",referencedColumnName = "id", unique = true)
    private UsuarioEntity usuario;

    public VerificadorEmailEntity(Long id, Instant expiration, UUID uuid, UsuarioEntity usuario) {
        this.id = id;
        this.expiration = expiration;
        this.uuid = uuid;
        this.usuario = usuario;
    }
}
