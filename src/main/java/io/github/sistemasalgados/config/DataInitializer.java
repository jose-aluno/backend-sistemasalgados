package io.github.sistemasalgados.config;

import io.github.sistemasalgados.patterns.factory.*;
import io.github.sistemasalgados.repository.SaborRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final SaborRepository saborRepository;
    private final FrangoSaborFactory frangoFactory;
    private final CatupirySaborFactory catupirFactory;
    private final CarneSaborFactory carneFactory;
    private final QueijoSaborFactory queijoFactory;
    private final PalmitoSaborFactory palmitoFactory;
    private final CamaraoSaborFactory camaraoFactory;

    public DataInitializer(
            SaborRepository saborRepository,
            FrangoSaborFactory frangoFactory,
            CatupirySaborFactory catupirFactory,
            CarneSaborFactory carneFactory,
            QueijoSaborFactory queijoFactory,
            PalmitoSaborFactory palmitoFactory,
            CamaraoSaborFactory camaraoFactory) {
        this.saborRepository = saborRepository;
        this.frangoFactory = frangoFactory;
        this.catupirFactory = catupirFactory;
        this.carneFactory = carneFactory;
        this.queijoFactory = queijoFactory;
        this.palmitoFactory = palmitoFactory;
        this.camaraoFactory = camaraoFactory;
    }

    @Override
    public void run(String... args) {
        if (saborRepository.count() == 0) {
            saborRepository.save(frangoFactory.criar(100));
            saborRepository.save(catupirFactory.criar(100));
            saborRepository.save(carneFactory.criar(100));
            saborRepository.save(queijoFactory.criar(100));
            saborRepository.save(palmitoFactory.criar(80));
            saborRepository.save(camaraoFactory.criar(50));

            System.out.println("Sabores inicializados com sucesso!");
        } else {
            System.out.println("Sabores já existem no banco, seed ignorada.");
        }
    }
}