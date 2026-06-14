package io.github.sistemasalgados.repository;

import io.github.sistemasalgados.model.Movimento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MovimentoRepository extends JpaRepository<Movimento, Long> {
    List<Movimento> findByPedidoId(Long pedidoId);
    List<Movimento> findByPedidoClienteId(Long clienteId);
}