package io.github.sistemasalgados.patterns.factory;

import io.github.sistemasalgados.model.Sabor;
import org.springframework.stereotype.Component;

@Component("catupiryFactory")
public class CatupirySaborFactory implements SaborFactory {

    @Override
    public Sabor criar(int quantidadeInicial) {
        return Sabor.builder()
                .nome("Catupiry")
                .preco(9.50)
                .promocao(false)
                .quantidadeEstoque(quantidadeInicial)
                .build();
    }
}