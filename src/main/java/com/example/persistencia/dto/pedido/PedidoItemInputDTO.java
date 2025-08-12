package com.example.persistencia.dto.pedido;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PedidoItemInputDTO {
    @NotNull
    private  Long produtoId;

    @Positive
    private Integer quantidade;

    public @NotNull Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(@NotNull Long produtoId) {
        this.produtoId = produtoId;
    }

    public @Positive Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(@Positive Integer quantidade) {
        this.quantidade = quantidade;
    }
}
