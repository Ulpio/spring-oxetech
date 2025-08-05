package com.example.persistencia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "o nome não pode ser nulo")
    @Size(min = 3,max = 100,message = "o nome deve ter entre 3 e 100 caracteres")
    private String nome;

    @Email(message = "email inválido")
    @NotBlank(message = "o email não pode ser nulo")
    @Size(max = 100,message = "o email deve ter até 100 caracteres")
    private String email;

    private String cpf;

    @Past(message = "deve ser uma data anterior a atual")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataNascimento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "o nome não pode ser nulo") @Size(min = 3, max = 100, message = "o nome deve ter entre 3 e 100 caracteres") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "o nome não pode ser nulo") @Size(min = 3, max = 100, message = "o nome deve ter entre 3 e 100 caracteres") String nome) {
        this.nome = nome;
    }

    public @Email(message = "email inválido") @NotBlank(message = "o email não pode ser nulo") @Size(max = 100, message = "o email deve ter até 100 caracteres") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "email inválido") @NotBlank(message = "o email não pode ser nulo") @Size(max = 100, message = "o email deve ter até 100 caracteres") String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public @Past(message = "deve ser uma data anterior a atual") LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(@Past(message = "deve ser uma data anterior a atual") LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}

