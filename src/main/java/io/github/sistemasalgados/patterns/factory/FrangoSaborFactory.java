package io.github.sistemasalgados.patterns.factory;

import io.github.sistemasalgados.model.Sabor;
import org.springframework.stereotype.Component;

@Component("frangoFactory")
public class FrangoSaborFactory implements SaborFactory {

    @Override
    public Sabor criar(int quantidadeInicial) {
        return Sabor.builder()
                .nome("Frango")
                .preco(8.00)
                .promocao(false)
                .quantidadeEstoque(quantidadeInicial)
                .build();
    }
}