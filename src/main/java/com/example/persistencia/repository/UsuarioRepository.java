package com.example.persistencia.repository;

import com.example.persistencia.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Page<Usuario> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
    Page<Usuario> findByEmailContainingIgnoreCase(String email, Pageable pageable);
    Page<Usuario> findByNomeContainingIgnoreCaseAndEmailContainingIgnoreCase(String nome,String email,Pageable pageable);
}
