package com.pedidos.ms_pedidos.service;

import com.pedidos.ms_pedidos.dto.PedidoRequestDto;
import com.pedidos.ms_pedidos.dto.PedidoResponseDto;
import com.pedidos.ms_pedidos.model.Pedido;
import com.pedidos.ms_pedidos.model.Status;
import com.pedidos.ms_pedidos.repository.PedidoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {
    private final PedidoRepository repository;

    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }

    public PedidoResponseDto cadastrarPedido(PedidoRequestDto pedidoDto) {
        Pedido pedido = new Pedido();
        BeanUtils.copyProperties(pedidoDto, pedido);
        Status status = Status.AGUARDANDO_PAGAMENTO;
        pedido.setStatus(status);
        pedido.setData(LocalDate.now());
        pedido.calcularTotal();
        repository.save(pedido);
        return new PedidoResponseDto(pedido.getId(), pedido.getStatus(), pedido.getCpf(),
                pedido.getItens(), pedido.getValorTotal(), pedido.getData());
    }

    public List<PedidoResponseDto> obterTodos() {
        return repository.findAll().stream()
                .map(pedido -> new PedidoResponseDto(pedido.getId(), pedido.getStatus(),
                        pedido.getCpf(), pedido.getItens(), pedido.getValorTotal(), pedido.getData()))
                .collect(Collectors.toList());
    }

}
