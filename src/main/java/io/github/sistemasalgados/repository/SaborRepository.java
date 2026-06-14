package io.github.sistemasalgados.repository;

import io.github.sistemasalgados.model.Sabor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SaborRepository extends JpaRepository<Sabor, Long> {
    List<Sabor> findByPromocaoTrue();
    List<Sabor> findByQuantidadeEstoqueGreaterThan(Integer quantidade);
}