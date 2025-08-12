package com.example.persistencia.repository;


import com.example.persistencia.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {
    Page<Produto> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

    Page<Produto> findByPrecoGreaterThan(BigDecimal PrecoMin, Pageable pageable);

    Page<Produto> findByCategoriaId(Long id,Pageable pageable);

    @Query("""
            SELECT p FROM Produto p
            WHERE (:nome IS NULL OR LOWER(p.nome) LIKE LOWER(CONCAT('%',:nome,'%')))
            AND (:precoMin IS NULL OR p.preco > :precoMin)
            AND (:categoriaID IS NULL OR p.categoria.id = :categoriaID)
            """)
    Page<Produto> filtrar(
            @Param("nome") String nome,
            @Param("precoMin") BigDecimal precoMin,
            @Param("categoriaId") Long categoriaId,
            Pageable pageable
    );

    boolean existsByCodigoBarras(String codigoBarras);
}
