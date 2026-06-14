package io.github.sistemasalgados.patterns.command;

import io.github.sistemasalgados.model.Movimento;
import io.github.sistemasalgados.model.Pedido;
import io.github.sistemasalgados.model.enums.StatusPedido;
import io.github.sistemasalgados.model.enums.TipoMovimento;
import io.github.sistemasalgados.repository.MovimentoRepository;
import io.github.sistemasalgados.repository.PedidoRepository;
import io.github.sistemasalgados.repository.SaborRepository;
import io.github.sistemasalgados.patterns.observer.PedidoObserver;

import java.time.LocalDateTime;
import java.util.List;

public class RealizarPedidoCommand implements Command {

    private final Pedido pedido;
    private final PedidoRepository pedidoRepository;
    private final MovimentoRepository movimentoRepository;
    private final SaborRepository saborRepository;
    private final List<PedidoObserver> observers;

    public RealizarPedidoCommand(
            Pedido pedido,
            PedidoRepository pedidoRepository,
            MovimentoRepository movimentoRepository,
            SaborRepository saborRepository,
            List<PedidoObserver> observers) {
        this.pedido = pedido;
        this.pedidoRepository = pedidoRepository;
        this.movimentoRepository = movimentoRepository;
        this.saborRepository = saborRepository;
        this.observers = observers;
    }

    @Override
    public void execute() {
        pedido.setStatus(StatusPedido.ATIVO);
        pedido.setDataPedido(LocalDateTime.now());
        pedidoRepository.save(pedido);

        var sabor = pedido.getSabor();
        sabor.setQuantidadeEstoque(sabor.getQuantidadeEstoque() - pedido.getQuantidade());
        saborRepository.save(sabor);

        Movimento movimento = Movimento.builder()
                .pedido(pedido)
                .tipo(TipoMovimento.SAIDA)
                .quantidadeMovida(pedido.getQuantidade())
                .valorMovido(pedido.getValorTotal())
                .dataMovimento(LocalDateTime.now())
                .observacao("Pedido realizado")
                .build();
        movimentoRepository.save(movimento);

        observers.forEach(o -> o.notificar(pedido));
    }
}