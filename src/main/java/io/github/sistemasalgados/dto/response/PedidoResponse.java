package io.github.sistemasalgados.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PedidoResponse {
    private Long id;
    private String sabor;
    private int quantidade;
    private double valorTotal;
    private String status;
    private LocalDateTime dataPedido;
}