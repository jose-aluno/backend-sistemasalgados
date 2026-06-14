package io.github.sistemasalgados.controller;

import io.github.sistemasalgados.dto.request.PedidoRequest;
import io.github.sistemasalgados.dto.response.HistoricoResponse;
import io.github.sistemasalgados.dto.response.PedidoResponse;
import io.github.sistemasalgados.service.PedidoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<PedidoResponse> realizar(
            HttpServletRequest request,
            @Valid @RequestBody PedidoRequest pedidoRequest) {
        Long clienteId = (Long) request.getAttribute("clienteId");
        return ResponseEntity.ok(pedidoService.realizar(clienteId, pedidoRequest));
    }

    @DeleteMapping("/{pedidoId}/estornar")
    public ResponseEntity<PedidoResponse> estornar(
            HttpServletRequest request,
            @PathVariable Long pedidoId) {
        Long clienteId = (Long) request.getAttribute("clienteId");
        return ResponseEntity.ok(pedidoService.estornar(pedidoId, clienteId));
    }

    @GetMapping("/historico")
    public ResponseEntity<HistoricoResponse> historico(HttpServletRequest request) {
        Long clienteId = (Long) request.getAttribute("clienteId");
        return ResponseEntity.ok(pedidoService.historico(clienteId));
    }
}