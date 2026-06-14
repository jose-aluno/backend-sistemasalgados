package io.github.sistemasalgados.patterns.factory;

import io.github.sistemasalgados.model.Sabor;
import org.springframework.stereotype.Component;

@Component("carneFactory")
public class CarneSaborFactory implements SaborFactory {

    @Override
    public Sabor criar(int quantidadeInicial) {
        return Sabor.builder()
                .nome("Carne")
                .preco(9.00)
                .promocao(false)
                .quantidadeEstoque(quantidadeInicial)
                .build();
    }
}