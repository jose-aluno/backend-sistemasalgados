package io.github.sistemasalgados.patterns.strategy;

import io.github.sistemasalgados.model.Sabor;
import org.springframework.stereotype.Component;

@Component("precoFidelidade")
public class PrecoFidelidadeStrategy implements PrecoStrategy {

    private static final double DESCONTO = 0.10;

    @Override
    public double calcularPreco(Sabor sabor, int quantidade) {
        double precoBase = sabor.getPreco() * quantidade;
        return precoBase - (precoBase * DESCONTO);
    }

    @Override
    public String getNome() {
        return "FIDELIDADE";
    }
}