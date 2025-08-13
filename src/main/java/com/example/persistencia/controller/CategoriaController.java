package com.example.persistencia.controller;


import com.example.persistencia.dto.categoria.CategoriaDTO;
import com.example.persistencia.dto.categoria.CategoriaInputDTO;
import com.example.persistencia.model.Categoria;
import com.example.persistencia.repository.CategoriaRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    private final CategoriaRepository repository;

    public CategoriaController(CategoriaRepository repository){
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<?>criar(@Valid @RequestBody CategoriaInputDTO dto){
        if (repository.existsByNomeIgnoreCase(dto.getNome())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("categoria ja existe");
        }
        Categoria c = new Categoria();
        c.setNome(dto.getNome());
        Categoria salvo = repository.save(c);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CategoriaDTO(salvo));
    }
    @GetMapping
    public Page<CategoriaDTO> listar(Pageable pageable){
        return repository.findAll(pageable).map(CategoriaDTO::new);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> filtrarPorID(@PathVariable Long id){
        return repository.findById(id)
                .<ResponseEntity>map(c -> ResponseEntity.ok(new CategoriaDTO(c)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("categoria não encontrada"));
    }
}
