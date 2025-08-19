package com.example.persistencia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3,max = 100)
    private String nome;
    @Positive
    private BigDecimal preco;
    @PositiveOrZero
    private Integer quantidade;

    @Pattern(regexp = "\\d{13}") @Column(unique = true)
    private String codigoBarras;

    @ManyToOne(optional = false)
    private Categoria categoria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank @Size(min = 3, max = 100) String getNome() {
        return nome;
    }

    public void setNome(@NotBlank @Size(min = 3, max = 100) String nome) {
        this.nome = nome;
    }

    public @Positive BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(@Positive BigDecimal preco) {
        this.preco = preco;
    }

    public @PositiveOrZero Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(@PositiveOrZero Integer quantidade) {
        this.quantidade = quantidade;
    }

    public @Pattern(regexp = "\\d{13}") String getCodigo_barras() {
        return codigoBarras;
    }

    public void setCodigo_barras(@Pattern(regexp = "\\d{13}") String codigo_barras) {
        this.codigoBarras = codigo_barras;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
