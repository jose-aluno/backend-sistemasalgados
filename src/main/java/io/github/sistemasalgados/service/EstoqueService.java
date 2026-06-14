package io.github.sistemasalgados.service;

import io.github.sistemasalgados.model.Sabor;
import io.github.sistemasalgados.repository.SaborRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstoqueService {

    private final SaborRepository saborRepository;

    public EstoqueService(SaborRepository saborRepository) {
        this.saborRepository = saborRepository;
    }

    public List<Sabor> listar() {
        return saborRepository.findAll();
    }
}