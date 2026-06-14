package io.github.sistemasalgados.patterns.command;

import io.github.sistemasalgados.model.Movimento;
import io.github.sistemasalgados.model.Pedido;
import io.github.sistemasalgados.model.enums.StatusPedido;
import io.github.sistemasalgados.model.enums.TipoMovimento;
import io.github.sistemasalgados.repository.MovimentoRepository;
import io.github.sistemasalgados.repository.PedidoRepository;
import io.github.sistemasalgados.repository.SaborRepository;

import java.time.LocalDateTime;

public class EstornarPedidoCommand implements Command {

    private final Pedido pedido;
    private final PedidoRepository pedidoRepository;
    private final MovimentoRepository movimentoRepository;
    private final SaborRepository saborRepository;

    public EstornarPedidoCommand(
            Pedido pedido,
            PedidoRepository pedidoRepository,
            MovimentoRepository movimentoRepository,
            SaborRepository saborRepository) {
        this.pedido = pedido;
        this.pedidoRepository = pedidoRepository;
        this.movimentoRepository = movimentoRepository;
        this.saborRepository = saborRepository;
    }

    @Override
    public void execute() {
        // 1. Marca pedido como estornado
        pedido.setStatus(StatusPedido.ESTORNADO);
        pedidoRepository.save(pedido);

        // 2. Devolve ao estoque
        var sabor = pedido.getSabor();
        sabor.setQuantidadeEstoque(sabor.getQuantidadeEstoque() + pedido.getQuantidade());
        saborRepository.save(sabor);

        // 3. Gera movimento de estorno
        Movimento movimento = Movimento.builder()
                .pedido(pedido)
                .tipo(TipoMovimento.ESTORNO)
                .quantidadeMovida(pedido.getQuantidade())
                .valorMovido(pedido.getValorTotal())
                .dataMovimento(LocalDateTime.now())
                .observacao("Pedido estornado")
                .build();
        movimentoRepository.save(movimento);
    }
}