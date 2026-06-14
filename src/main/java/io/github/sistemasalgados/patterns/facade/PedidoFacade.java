package io.github.sistemasalgados.patterns.facade;

import io.github.sistemasalgados.model.Cliente;
import io.github.sistemasalgados.model.Pedido;
import io.github.sistemasalgados.model.Sabor;
import io.github.sistemasalgados.patterns.command.EstornarPedidoCommand;
import io.github.sistemasalgados.patterns.command.RealizarPedidoCommand;
import io.github.sistemasalgados.patterns.observer.PedidoObserver;
import io.github.sistemasalgados.patterns.strategy.PrecoFidelidadeStrategy;
import io.github.sistemasalgados.patterns.strategy.PrecoNormalStrategy;
import io.github.sistemasalgados.patterns.strategy.PrecoPromocionalStrategy;
import io.github.sistemasalgados.patterns.strategy.PrecoStrategy;
import io.github.sistemasalgados.repository.MovimentoRepository;
import io.github.sistemasalgados.repository.PedidoRepository;
import io.github.sistemasalgados.repository.SaborRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoFacade {

    private final PedidoRepository pedidoRepository;
    private final MovimentoRepository movimentoRepository;
    private final SaborRepository saborRepository;
    private final List<PedidoObserver> observers;
    private final PrecoNormalStrategy precoNormal;
    private final PrecoPromocionalStrategy precoPromocional;
    private final PrecoFidelidadeStrategy precoFidelidade;

    public PedidoFacade(
            PedidoRepository pedidoRepository,
            MovimentoRepository movimentoRepository,
            SaborRepository saborRepository,
            List<PedidoObserver> observers,
            PrecoNormalStrategy precoNormal,
            PrecoPromocionalStrategy precoPromocional,
            PrecoFidelidadeStrategy precoFidelidade) {
        this.pedidoRepository = pedidoRepository;
        this.movimentoRepository = movimentoRepository;
        this.saborRepository = saborRepository;
        this.observers = observers;
        this.precoNormal = precoNormal;
        this.precoPromocional = precoPromocional;
        this.precoFidelidade = precoFidelidade;
    }

    public Pedido realizarPedido(Cliente cliente, Sabor sabor, int quantidade) {
        if (sabor.getQuantidadeEstoque() < quantidade) {
            throw new IllegalStateException(
                String.format("Estoque insuficiente para '%s'. Disponível: %d",
                    sabor.getNome(), sabor.getQuantidadeEstoque()));
        }

        int totalPedidos = pedidoRepository.findByClienteId(cliente.getId()).size();
        PrecoStrategy strategy;
        if (sabor.isPromocao()) {
            strategy = precoPromocional;
        } else if (totalPedidos >= 5) {
            strategy = precoFidelidade;
        } else {
            strategy = precoNormal;
        }
        double valorTotal = strategy.calcularPreco(sabor, quantidade);

        Pedido pedido = Pedido.builder()
                .cliente(cliente)
                .sabor(sabor)
                .quantidade(quantidade)
                .valorTotal(valorTotal)
                .build();

        new RealizarPedidoCommand(pedido, pedidoRepository, movimentoRepository, saborRepository, observers).execute();

        return pedido;
    }

    public Pedido estornarPedido(Pedido pedido) {
        new EstornarPedidoCommand(pedido, pedidoRepository, movimentoRepository, saborRepository).execute();
        return pedido;
    }
}