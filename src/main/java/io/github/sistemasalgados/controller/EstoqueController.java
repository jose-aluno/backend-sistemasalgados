package io.github.sistemasalgados.controller;

import io.github.sistemasalgados.model.Sabor;
import io.github.sistemasalgados.service.EstoqueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    private final EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @GetMapping
    public ResponseEntity<List<Sabor>> listar() {
        return ResponseEntity.ok(estoqueService.listar());
    }
}