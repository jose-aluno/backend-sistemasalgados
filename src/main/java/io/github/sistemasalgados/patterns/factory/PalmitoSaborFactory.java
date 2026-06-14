package io.github.sistemasalgados.patterns.factory;

import io.github.sistemasalgados.model.Sabor;
import org.springframework.stereotype.Component;

@Component("palmitoFactory")
public class PalmitoSaborFactory implements SaborFactory {

    @Override
    public Sabor criar(int quantidadeInicial) {
        return Sabor.builder()
                .nome("Palmito")
                .preco(10.00)
                .promocao(true)
                .quantidadeEstoque(quantidadeInicial)
                .build();
    }
}