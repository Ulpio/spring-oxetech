package com.example.persistencia.controller;

import com.example.persistencia.model.Usuario;
import com.example.persistencia.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jdk.javadoc.doclet.Reporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository userRepo;

    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody Usuario user, BindingResult result){
        if (result.hasErrors()){
            List<String> erros = result.getFieldErrors().stream()
                    .map(err -> err.getField() + ":" + err.getDefaultMessage())
                    .toList();
            return ResponseEntity.badRequest().body(erros);
        }
        Usuario salvo = userRepo.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    public List<Usuario> listar(){
        return userRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> listarPorID(@PathVariable Long id){
        Usuario usuario = userRepo.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok(usuario);
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id,@RequestBody Usuario usuarioAtualizado){
        return userRepo.findById(id)
                .map(usuarioExistente -> {
                    usuarioExistente.setNome(usuarioAtualizado.getNome());
                    usuarioExistente.setEmail(usuarioAtualizado.getEmail());

                    Usuario atualizado = userRepo.save(usuarioExistente);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    //PATCH -> Atualizar parcial
    @PatchMapping("/{id}")
    public ResponseEntity<Usuario> patchUsuario(@PathVariable Long id,@RequestBody Usuario dadosParciais){
        return userRepo.findById(id)
                .map(usuarioExistente -> {
                    if (dadosParciais.getNome() != null)
                        usuarioExistente.setNome(dadosParciais.getNome());
                    if (dadosParciais.getEmail() != null)
                        usuarioExistente.setEmail(dadosParciais.getEmail());

                    Usuario atualizado = userRepo.save(usuarioExistente);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deletar(@PathVariable Long id){
        return userRepo.findById(id)
                .<ResponseEntity>map(usuario -> {
                    userRepo.delete(usuario);
                    return ResponseEntity.noContent().build(); // 204 NO content
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // 404
    }

}
