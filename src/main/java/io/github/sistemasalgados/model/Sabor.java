package io.github.sistemasalgados.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sabores")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Sabor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private Double preco;

    @Column(nullable = false)
    private Integer quantidadeEstoque;

    private boolean promocao;
}