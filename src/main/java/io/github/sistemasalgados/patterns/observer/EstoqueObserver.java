package io.github.sistemasalgados.patterns.observer;

import io.github.sistemasalgados.model.Pedido;
import org.springframework.stereotype.Component;

@Component
public class EstoqueObserver implements PedidoObserver {

    private static final int ESTOQUE_MINIMO = 10;

    @Override
    public void notificar(Pedido pedido) {
        int estoqueAtual = pedido.getSabor().getQuantidadeEstoque();
        if (estoqueAtual <= ESTOQUE_MINIMO) {
            System.out.printf("ALERTA: Estoque baixo de '%s'! Restam %d unidades.%n",
                    pedido.getSabor().getNome(), estoqueAtual);
        }
    }
}