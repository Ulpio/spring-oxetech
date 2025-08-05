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
    private UsuarioRepository userRepo;

    @PostMapping
    public ResponseEntity<Usuario> criar(@RequestBody Usuario user){
        Usuario salvo = userRepo.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    public List<Usuario> listar(){
        return userRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> listarPorID(@PathVariable Long id){
        return userRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //PUT

    //DELETE

}
