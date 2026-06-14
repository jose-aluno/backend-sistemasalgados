package io.github.sistemasalgados.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PedidoRequest {
    @NotNull
    private Long saborId;

    @Min(1)
    private int quantidade;
}