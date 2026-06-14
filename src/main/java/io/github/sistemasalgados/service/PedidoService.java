package io.github.sistemasalgados.service;

import io.github.sistemasalgados.dto.request.PedidoRequest;
import io.github.sistemasalgados.dto.response.HistoricoResponse;
import io.github.sistemasalgados.dto.response.PedidoResponse;
import io.github.sistemasalgados.model.Cliente;
import io.github.sistemasalgados.model.Pedido;
import io.github.sistemasalgados.model.Sabor;
import io.github.sistemasalgados.model.enums.StatusPedido;
import io.github.sistemasalgados.patterns.facade.PedidoFacade;
import io.github.sistemasalgados.repository.ClienteRepository;
import io.github.sistemasalgados.repository.PedidoRepository;
import io.github.sistemasalgados.repository.SaborRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final SaborRepository saborRepository;
    private final PedidoFacade pedidoFacade;

    public PedidoService(
            PedidoRepository pedidoRepository,
            ClienteRepository clienteRepository,
            SaborRepository saborRepository,
            PedidoFacade pedidoFacade) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.saborRepository = saborRepository;
        this.pedidoFacade = pedidoFacade;
    }

    public PedidoResponse realizar(Long clienteId, PedidoRequest request) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        Sabor sabor = saborRepository.findById(request.getSaborId())
                .orElseThrow(() -> new IllegalArgumentException("Sabor não encontrado"));

        Pedido pedido = pedidoFacade.realizarPedido(cliente, sabor, request.getQuantidade());
        return toResponse(pedido);
    }

    public PedidoResponse estornar(Long pedidoId, Long clienteId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

        if (!pedido.getCliente().getId().equals(clienteId)) {
                throw new IllegalArgumentException("Pedido não pertence a este cliente");
        }

        if (pedido.getStatus() == StatusPedido.ESTORNADO) {
                throw new IllegalStateException("Pedido já foi estornado");
        }

        Pedido estornado = pedidoFacade.estornarPedido(pedido);
        return toResponse(estornado);
    }

    public HistoricoResponse historico(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        List<Pedido> pedidos = pedidoRepository.findByClienteId(clienteId);

        double totalGasto = pedidos.stream()
            .filter(p -> p.getStatus() == StatusPedido.ATIVO)
            .mapToDouble(Pedido::getValorTotal)
            .sum();

        List<PedidoResponse> respostas = pedidos.stream()
                .map(this::toResponse)
                .toList();

        return new HistoricoResponse(cliente.getNome(), pedidos.size(), totalGasto, respostas);
    }

    private PedidoResponse toResponse(Pedido pedido) {
        return new PedidoResponse(
                pedido.getId(),
                pedido.getSabor().getNome(),
                pedido.getQuantidade(),
                pedido.getValorTotal(),
                pedido.getStatus().name(),
                pedido.getDataPedido()
        );
    }
}