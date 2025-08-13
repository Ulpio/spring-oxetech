package com.example.persistencia.dto.pedido;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class PedidoInputDTO {
    @NotNull
    private Long usuarioId;

    @NotEmpty
    private List<PedidoItemInputDTO> itens;

    public @NotNull Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(@NotNull Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public @NotEmpty List<PedidoItemInputDTO> getItens() {
        return itens;
    }

    public void setItens(@NotEmpty List<PedidoItemInputDTO> itens) {
        this.itens = itens;
    }
}
