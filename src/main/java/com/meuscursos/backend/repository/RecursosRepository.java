package com.meuscursos.backend.repository;

import com.meuscursos.backend.entity.Recursos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecursosRepository extends JpaRepository<Recursos, Long> {
}
