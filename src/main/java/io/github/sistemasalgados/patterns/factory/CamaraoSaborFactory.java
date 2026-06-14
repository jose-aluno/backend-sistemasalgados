package io.github.sistemasalgados.patterns.factory;

import io.github.sistemasalgados.model.Sabor;
import org.springframework.stereotype.Component;

@Component("camaraoFactory")
public class CamaraoSaborFactory implements SaborFactory {

    @Override
    public Sabor criar(int quantidadeInicial) {
        return Sabor.builder()
                .nome("Camarão")
                .preco(12.00)
                .promocao(true)
                .quantidadeEstoque(quantidadeInicial)
                .build();
    }
}