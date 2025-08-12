package com.example.persistencia.dto.categoria;

import com.example.persistencia.model.Categoria;

public class CategoriaDTO {
    private Long id;
    private String nome;

    public CategoriaDTO(Categoria c) {
        this.id = c.getId();
        this.nome = c.getNome();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
