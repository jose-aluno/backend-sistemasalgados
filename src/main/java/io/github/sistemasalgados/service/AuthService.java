package io.github.sistemasalgados.service;

import io.github.sistemasalgados.dto.request.CadastroRequest;
import io.github.sistemasalgados.dto.request.LoginRequest;
import io.github.sistemasalgados.dto.response.LoginResponse;
import io.github.sistemasalgados.model.Cliente;
import io.github.sistemasalgados.repository.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final ClienteRepository clienteRepository;
    private final JwtService jwtService;

    public AuthService(ClienteRepository clienteRepository, JwtService jwtService) {
        this.clienteRepository = clienteRepository;
        this.jwtService = jwtService;
    }

    public LoginResponse login(LoginRequest request) {
        Cliente cliente = clienteRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Email não encontrado"));

        if (!cliente.getSenha().equals(request.getSenha())) {
            throw new IllegalArgumentException("Senha incorreta");
        }

        String token = jwtService.gerarToken(cliente.getId(), cliente.getEmail());
        return new LoginResponse(cliente.getId(), cliente.getNome(), cliente.getEmail(), token);
    }

    public LoginResponse cadastrar(CadastroRequest request) {
        if (clienteRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        Cliente cliente = Cliente.builder()
                .nome(request.getNome())
                .email(request.getEmail())
                .senha(request.getSenha())
                .build();

        clienteRepository.save(cliente);
        String token = jwtService.gerarToken(cliente.getId(), cliente.getEmail());
        return new LoginResponse(cliente.getId(), cliente.getNome(), cliente.getEmail(), token);
    }
}