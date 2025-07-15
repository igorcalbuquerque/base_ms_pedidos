package com.pedidos.ms_pedidos.controller;

import com.pedidos.ms_pedidos.dto.PedidoRequestDto;
import com.pedidos.ms_pedidos.dto.PedidoResponseDto;
import com.pedidos.ms_pedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService service;

    @PostMapping
    public ResponseEntity<PedidoResponseDto> cadastrarPedido(@RequestBody PedidoRequestDto pedidoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarPedido(pedidoDto));
    }

    @GetMapping
    public List<PedidoResponseDto> obterTodos() {
        return service.obterTodos();
    }
}
