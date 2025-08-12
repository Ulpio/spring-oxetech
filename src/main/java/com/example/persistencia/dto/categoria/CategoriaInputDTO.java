package com.example.persistencia.dto.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoriaInputDTO {

    @NotBlank @Size(min = 3,max = 100)
    private String nome;

    public @NotBlank @Size(min = 3, max = 100) String getNome() {
        return nome;
    }

    public void setNome(@NotBlank @Size(min = 3, max = 100) String nome) {
        this.nome = nome;
    }
}
