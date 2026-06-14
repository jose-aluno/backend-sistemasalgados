package io.github.sistemasalgados.patterns.strategy;

import io.github.sistemasalgados.model.Sabor;
import org.springframework.stereotype.Component;

@Component("precoNormal")
public class PrecoNormalStrategy implements PrecoStrategy {

    @Override
    public double calcularPreco(Sabor sabor, int quantidade) {
        return sabor.getPreco() * quantidade;
    }

    @Override
    public String getNome() {
        return "NORMAL";
    }
}