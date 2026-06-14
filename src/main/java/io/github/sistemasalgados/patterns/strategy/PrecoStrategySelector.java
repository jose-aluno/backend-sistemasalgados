package io.github.sistemasalgados.patterns.strategy;

import io.github.sistemasalgados.model.Sabor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class PrecoStrategySelector {

    private final PrecoStrategy precoNormal;
    private final PrecoStrategy precoPromocional;
    private final PrecoStrategy precoFidelidade;

    public PrecoStrategySelector(
            @Qualifier("precoNormal") PrecoStrategy precoNormal,
            @Qualifier("precoPromocional") PrecoStrategy precoPromocional,
            @Qualifier("precoFidelidade") PrecoStrategy precoFidelidade) {
        this.precoNormal = precoNormal;
        this.precoPromocional = precoPromocional;
        this.precoFidelidade = precoFidelidade;
    }

    public PrecoStrategy selecionar(Sabor sabor, int totalPedidosCliente) {
        if (sabor.isPromocao()) {
            return precoPromocional;
        }
        if (totalPedidosCliente >= 5) {
            return precoFidelidade;
        }
        return precoNormal;
    }
}