package com.pedidos.ms_pedidos.dto;

import com.pedidos.ms_pedidos.model.ItemPedido;

import java.util.List;

public record PedidoRequestDto(String cpf, List<ItemPedido> itens) {
}
