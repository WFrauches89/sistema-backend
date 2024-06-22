package com.meuscursos.backend.repository;

import com.meuscursos.backend.entity.PerfilUsuarioEntity;
import com.meuscursos.backend.entity.PermissaoPerfilRecursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissaoPerfilRecursosRepository extends JpaRepository<PermissaoPerfilRecursoEntity, Long> {
}
