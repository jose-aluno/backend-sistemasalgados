package io.github.sistemasalgados.patterns.observer;

import io.github.sistemasalgados.model.Pedido;
import org.springframework.stereotype.Component;

@Component
public class FinanceiroObserver implements PedidoObserver {

    @Override
    public void notificar(Pedido pedido) {
        System.out.printf("FINANCEIRO: Pedido #%d registrado — Valor: R$ %.2f | Cliente: %s%n",
                pedido.getId(),
                pedido.getValorTotal(),
                pedido.getCliente().getNome());
    }
}