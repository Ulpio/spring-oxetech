package com.example.persistencia.dto.pedido;

import com.example.persistencia.model.Pedido;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class PedidoDTO {
    private Long id;
    private Long usuarioId;
    private LocalDate dataCriacao;
    private BigDecimal total;
    private List<PedidoItemDTO> itens;

    public PedidoDTO(Pedido p,List<PedidoItemDTO> itens){
        this.id = p.getId();
        this.usuarioId = p.getUsuario().getId();
        this.dataCriacao = p.getDataCriacao();
        this.total = p.getTotal();
        this.itens = itens;
    }

    public Long getId() {
        return id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public List<PedidoItemDTO> getItens() {
        return itens;
    }
}
