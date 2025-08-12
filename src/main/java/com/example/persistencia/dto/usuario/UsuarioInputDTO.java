package com.example.persistencia.dto.usuario;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class UsuarioInputDTO {
    @NotBlank @Size(min = 3,max = 100)
    private String nome;

    @Email @NotBlank @Size(max = 100)
    private String email;

    @Pattern(regexp = "\\d{11}",message = "CPF deve ter 11 digitos")
    private String cpf;

    @Past
    private LocalDate dataNascimento;

    public @NotBlank @Size(min = 3, max = 100) String getNome() {
        return nome;
    }

    public void setNome(@NotBlank @Size(min = 3, max = 100) String nome) {
        this.nome = nome;
    }

    public @Email @NotBlank @Size(max = 100) String getEmail() {
        return email;
    }

    public void setEmail(@Email @NotBlank @Size(max = 100) String email) {
        this.email = email;
    }

    public @Pattern(regexp = "\\d{11}", message = "CPF deve ter 11 digitos") String getCpf() {
        return cpf;
    }

    public void setCpf(@Pattern(regexp = "\\d{11}", message = "CPF deve ter 11 digitos") String cpf) {
        this.cpf = cpf;
    }

    public @Past LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(@Past LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
