package com.example.persistencia.dto.pedido;

import com.example.persistencia.model.PedidoItem;

import java.math.BigDecimal;

public class PedidoItemDTO {
    private Long produtoId;
    private String produtoNome;
    private  Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal subtotal;

    public PedidoItemDTO(PedidoItem i){
        this.produtoId = i.getProduto().getId();
        this.produtoNome = i.getProduto().getNome();
        this.quantidade = i.getQuantidade();
        this.precoUnitario = i.getPrecoUnitario();
        this.subtotal = i.getSubtotal();
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public String getProdutoNome() {
        return produtoNome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }
}
