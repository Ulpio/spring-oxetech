package com.example.persistencia.repository;

import com.example.persistencia.model.Categoria;
import com.example.persistencia.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido,Long> {
    Page<Pedido> findByUsuarioId(Long usuarioId, Pageable pageable);
}
