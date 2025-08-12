package com.example.persistencia.dto.usuario;

import com.example.persistencia.model.Usuario;

import java.time.LocalDate;

public class UsuarioDTO {
    Long id;
    String nome;
    String email;
    String cpf;
    LocalDate dataNacimento;

    public UsuarioDTO(Usuario u){
        this.id = u.getId();
        this.nome = u.getNome();
        this.email = u.getEmail();
        this.cpf = u.getCpf();
        this.dataNacimento = u.getDataNascimento();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNacimento() {
        return dataNacimento;
    }
}
