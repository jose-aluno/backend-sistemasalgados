package io.github.sistemasalgados.patterns.observer;

import io.github.sistemasalgados.model.Pedido;

public interface PedidoObserver {
    void notificar(Pedido pedido);
}