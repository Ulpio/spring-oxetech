package com.example.persistencia.controller;

import com.example.persistencia.dto.produto.ProdutoDTO;
import com.example.persistencia.dto.produto.ProdutoInputDTO;
import com.example.persistencia.model.Categoria;
import com.example.persistencia.model.Produto;
import com.example.persistencia.repository.CategoriaRepository;
import com.example.persistencia.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoRepository repository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoController(ProdutoRepository repository,CategoriaRepository categoriaRepository){
        this.repository = repository;
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody ProdutoInputDTO dto){
        if (!categoriaRepository.existsById(dto.getCategoriaId())){
            return ResponseEntity.badRequest().body("categoria nao existente");
        }
        if (repository.existsByCodigoBarras(dto.getCodigoBarras())){
            return  ResponseEntity.badRequest().body("codigo de barras ja cadastrado");
        }

        Categoria cat = categoriaRepository.findById(dto.getCategoriaId()).get();
        Produto p = new Produto();
        p.setNome(dto.getNome());
        p.setPreco(dto.getPreco());
        p.setQuantidade(dto.getQuantidade());
        p.setCodigo_barras(dto.getCodigoBarras());
        p.setCategoria(cat);

        Produto salvo = repository.save(p);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ProdutoDTO(salvo));
    }

    @GetMapping
    public Page<ProdutoDTO> listar(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) BigDecimal precoMin,
            @RequestParam(required = false) Long categoriaId,
            Pageable pageable
    ) {
        // normaliza nome: vazio/blank vira null
        String nomeTrim = (nome != null && !nome.isBlank()) ? nome.trim() : null;

        boolean semFiltros = (nomeTrim == null) && (precoMin == null) && (categoriaId == null);

        Page<Produto> page = semFiltros
                ? repository.findAll(pageable)
                : repository.filtrar(nomeTrim, precoMin, categoriaId, pageable);

        return page.map(ProdutoDTO::new);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        return repository.findById(id)
                .<ResponseEntity>map(p -> ResponseEntity.ok(new ProdutoDTO(p)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("produto não encontrado"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id,@Valid @RequestBody ProdutoInputDTO dto){
        return repository.findById(id).map(p -> {
            if (!categoriaRepository.existsById(dto.getCategoriaId())){
                return ResponseEntity.badRequest().body("Categoria Inexistente");
            }
            Categoria cat = categoriaRepository.findById(dto.getCategoriaId()).get();
            p.setNome(dto.getNome());
            p.setPreco(dto.getPreco());
            p.setQuantidade(dto.getQuantidade());
            p.setCodigo_barras(dto.getCodigoBarras());
            p.setCategoria(cat);
            Produto salvo = repository.save(p);
            return ResponseEntity.ok(new ProdutoDTO(salvo));
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("produto não encontrado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id){
        if (!repository.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("produto não encontrado");
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
