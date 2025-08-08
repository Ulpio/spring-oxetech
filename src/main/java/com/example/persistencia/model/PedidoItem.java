package com.example.persistencia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Entity
public class PedidoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Pedido pedido;
    @ManyToOne(optional = false)
    private Produto produto;
    @Positive
    private Integer quantidade;
    @Positive
    private BigDecimal precoUnitario;
    @Positive
    private BigDecimal subtotal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public @Positive Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(@Positive Integer quantidade) {
        this.quantidade = quantidade;
    }

    public @Positive BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(@Positive BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public @Positive BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(@Positive BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}
