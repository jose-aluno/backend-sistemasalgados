package io.github.sistemasalgados.patterns.factory;

import io.github.sistemasalgados.model.Sabor;
import org.springframework.stereotype.Component;

@Component("queijoFactory")
public class QueijoSaborFactory implements SaborFactory {

    @Override
    public Sabor criar(int quantidadeInicial) {
        return Sabor.builder()
                .nome("Queijo")
                .preco(8.50)
                .promocao(false)
                .quantidadeEstoque(quantidadeInicial)
                .build();
    }
}