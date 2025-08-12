package com.example.persistencia.dto.pedido;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public class PedidoInputDTO {
    @NotNull
    private  Long produtoId;

    @Positive
    private List<PedidoItemInputDTO> itens;

    public @NotNull Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(@NotNull Long produtoId) {
        this.produtoId = produtoId;
    }

    public @Positive List<PedidoItemInputDTO> getItens() {
        return itens;
    }

    public void setItens(@Positive List<PedidoItemInputDTO> itens) {
        this.itens = itens;
    }
}
