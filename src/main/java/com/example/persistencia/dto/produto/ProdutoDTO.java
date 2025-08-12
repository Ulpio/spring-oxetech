package com.example.persistencia.dto.produto;

import com.example.persistencia.model.Produto;

import java.math.BigDecimal;

public class ProdutoDTO {
    private Long id;
    private String nome;
    private BigDecimal preco;
    private Integer quantidade;
    private String codigoBarras;
    private String categoriaNome;

    public ProdutoDTO(Produto p){
        this.id = p.getId();
        this.nome = p.getNome();
        this.preco = p.getPreco();
        this.quantidade = p.getQuantidade();
        this.codigoBarras = p.getCodigo_barras();
        this.categoriaNome = p.getCategoria().getNome();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public String getCategoriaNome() {
        return categoriaNome;
    }
}
