package com.example.persistencia.dto.produto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class ProdutoInputDTO {
    @NotBlank @Size(min = 3,max = 100)
    private String nome;

    @Positive
    private BigDecimal preco;

    @PositiveOrZero
    private Integer quantidade;

    private String codigoBarras;

    @NotNull
    private Long categoriaId;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }
}
