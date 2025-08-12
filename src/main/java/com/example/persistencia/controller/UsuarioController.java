package com.example.persistencia.controller;

import com.example.persistencia.dto.usuario.UsuarioDTO;
import com.example.persistencia.dto.usuario.UsuarioInputDTO;
import com.example.persistencia.model.Usuario;
import com.example.persistencia.repository.UsuarioRepository;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jdk.javadoc.doclet.Reporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioRepository repository;

    public UsuarioController(UsuarioRepository repository){
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<?>criar(@Valid @RequestBody UsuarioInputDTO dto){
        if (repository.existsByEmail(dto.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("email ja cadastrado");
        }
        if (repository.existsByCpf(dto.getCpf())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("cpf ja cadastrado");
        }

        Usuario u = new Usuario();
        u.setNome(dto.getNome());
        u.setEmail(dto.getEmail());
        u.setCpf(dto.getCpf());
        u.setDataNascimento(dto.getDataNascimento());
        Usuario salvo = repository.save(u);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UsuarioDTO(salvo));
    }

    @GetMapping
    public Page<UsuarioDTO> listar(@RequestParam(required = false)String nome,Pageable pageable){
        Page<Usuario> page = (nome == null || nome.isBlank())
            ?repository.findAll(pageable)
            :repository.findByNomeContainingIgnoreCase(nome,pageable);
        return page.map(UsuarioDTO::new);
    }

    @GetMapping("{/id}")
    public ResponseEntity<?> buscarPorID(@PathVariable Long id){
        return repository.findById(id)
                .<ResponseEntity>map(u -> ResponseEntity.ok(new UsuarioDTO(u)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("usuario não encontrado"));
    }
}