package io.github.sistemasalgados.patterns.factory;

import io.github.sistemasalgados.model.Sabor;

public interface SaborFactory {
    Sabor criar(int quantidadeInicial);
}