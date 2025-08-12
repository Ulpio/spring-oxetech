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

    @Pattern(regexp = "\\d{11}",message = "codigo de barras deve ter 11 digitos")
    private String codigoBarras;

    @NotNull
    private Long categoriaId;

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

    public @Pattern(regexp = "\\d{11}", message = "codigo de barras deve ter 11 digitos") String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(@Pattern(regexp = "\\d{11}", message = "codigo de barras deve ter 11 digitos") String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public @NotNull Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(@NotNull Long categoriaId) {
        this.categoriaId = categoriaId;
    }
}
