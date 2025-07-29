package com.example.persistencia.controller;

import com.example.persistencia.model.Usuario;
import com.example.persistencia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository userrepo;

    @PostMapping
    public ResponseEntity<Usuario> criar(@RequestBody Usuario user){
        Usuario salvo = userrepo.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    public List<Usuario> listar(){
        return userrepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> listarPorID(@PathVariable Long id){
        return userrepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
