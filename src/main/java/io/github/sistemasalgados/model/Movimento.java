package io.github.sistemasalgados.model;

import io.github.sistemasalgados.model.enums.TipoMovimento;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimentos")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Movimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMovimento tipo;

    @Column(nullable = false)
    private Integer quantidadeMovida;

    @Column(nullable = false)
    private Double valorMovido;

    @Column(nullable = false)
    private LocalDateTime dataMovimento;

    private String observacao;
}