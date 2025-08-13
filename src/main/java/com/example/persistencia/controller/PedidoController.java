package com.example.persistencia.controller;

import com.example.persistencia.dto.pedido.PedidoDTO;
import com.example.persistencia.dto.pedido.PedidoInputDTO;
import com.example.persistencia.dto.pedido.PedidoItemDTO;
import com.example.persistencia.dto.pedido.PedidoItemInputDTO;
import com.example.persistencia.model.Pedido;
import com.example.persistencia.model.PedidoItem;
import com.example.persistencia.model.Produto;
import com.example.persistencia.repository.PedidoRepository;
import com.example.persistencia.repository.ProdutoRepository;
import com.example.persistencia.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoRepository pedidoRepo;
    private final ProdutoRepository produtoRepo;
    private final UsuarioRepository usuarioRepo;

    public PedidoController(
            PedidoRepository pedidoRepo,
            ProdutoRepository produtoRepo,
            UsuarioRepository usuarioRepo
    ){
        this.pedidoRepo = pedidoRepo;
        this.produtoRepo = produtoRepo;
        this.usuarioRepo =usuarioRepo;
    }

    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody PedidoInputDTO dto){
        if (!usuarioRepo.existsById(dto.getUsuarioId())){
            return ResponseEntity.badRequest().body("usuario inexistente");
        }

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuarioRepo.findById(dto.getUsuarioId()).get());
        pedido.setDataCriacao(LocalDate.now());

        List<PedidoItem> itens = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (PedidoItemInputDTO itemDto : dto.getItens()){
            var produtoOpt = produtoRepo.findById(itemDto.getProdutoId());
            if (produtoOpt.isEmpty()){
                return ResponseEntity.badRequest().body("produto nao encontrado: " + itemDto.getProdutoId());
            }
            Produto produto = produtoOpt.get();
            PedidoItem item = new PedidoItem();
            item.setPedido(pedido);
            item.setProduto(produto);
            item.setQuantidade(itemDto.getQuantidade());
            item.setPrecoUnitario(produto.getPreco());
            BigDecimal subtotal = produto.getPreco().multiply(BigDecimal.valueOf(itemDto.getQuantidade()));
            item.setSubtotal(subtotal);
            itens.add(item);
            total = total.add(subtotal);
        }

        pedido.setItens(itens);
        pedido.setTotal(total);

        Pedido salvo = pedidoRepo.save(pedido);
        List<PedidoItemDTO> itensDTO = salvo.getItens().stream().map(PedidoItemDTO::new).toList();
        return ResponseEntity.status(HttpStatus.CREATED).body(new PedidoDTO(salvo,itensDTO));
    }

    @GetMapping
    public Page<PedidoDTO> listar(@RequestParam(required = false) Long usuarioId, Pageable pageable){
        Page<Pedido> page = usuarioId == null
                ? pedidoRepo.findAll(pageable)
                : pedidoRepo.findByUsuarioId(usuarioId,pageable);
        return page.map(p -> new PedidoDTO(p,p.getItens().stream().map(PedidoItemDTO::new).toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        return pedidoRepo.findById(id)
                .<ResponseEntity<?>>map(p -> ResponseEntity.ok(new PedidoDTO(p,p.getItens().stream().map(PedidoItemDTO::new).toList())))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("pedido não encontrado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id){
        if (!pedidoRepo.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("pedido não encontrado");
        }
        pedidoRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
