package com.meuscursos.backend.repository.email;

import com.meuscursos.backend.entity.email.VerificadorEmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VerificadorEmailRepository extends JpaRepository<VerificadorEmailEntity, Long> {

    Optional<VerificadorEmailEntity> findByUuid(UUID uuid);
}
