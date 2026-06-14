package io.github.sistemasalgados.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class HistoricoResponse {
    private String cliente;
    private int totalPedidos;
    private double totalGasto;
    private List<PedidoResponse> pedidos;
}