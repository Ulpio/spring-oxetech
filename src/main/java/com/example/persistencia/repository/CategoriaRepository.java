package com.example.persistencia.repository;

import com.example.persistencia.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
    boolean existsByNomeIgnoreCase(String nome);
}
