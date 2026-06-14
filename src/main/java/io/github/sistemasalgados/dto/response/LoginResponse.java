package io.github.sistemasalgados.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private Long clienteId;
    private String nome;
    private String email;
    private String token;
}