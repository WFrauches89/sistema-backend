package com.meuscursos.backend.repository;

import com.meuscursos.backend.entity.RecursosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecursosRepository extends JpaRepository<RecursosEntity, Long> {
}
