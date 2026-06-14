package io.github.sistemasalgados.patterns.strategy;

import io.github.sistemasalgados.model.Sabor;

public interface PrecoStrategy {
    double calcularPreco(Sabor sabor, int quantidade);
    String getNome();
}