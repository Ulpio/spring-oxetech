package com.example.persistencia.controller;

import com.example.persistencia.repository.PedidoRepository;
import com.example.persistencia.repository.ProdutoRepository;
import com.example.persistencia.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
