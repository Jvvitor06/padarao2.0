package com.example.padaria_paotorrado.initializer;

import com.example.padaria_paotorrado.infrastructure.entitys.Padaria;
import com.example.padaria_paotorrado.infrastructure.repository.PadariaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final PadariaRepository padariaRepository;

    public DataInitializer(PadariaRepository padariaRepository) {
        this.padariaRepository = padariaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (padariaRepository.count() == 0) {
            padariaRepository.save(new Padaria(null, "Pão Francês", 0.50));
            padariaRepository.save(new Padaria(null, "Croissant", 2.00));
            padariaRepository.save(new Padaria(null, "Baguete", 3.50));
            padariaRepository.save(new Padaria(null, "Pão de Queijo", 1.20));
            padariaRepository.save(new Padaria(null, "Sonho", 3.00));
            padariaRepository.save(new Padaria(null, "Pão Integral", 1.00));
            padariaRepository.save(new Padaria(null, "Ciabatta", 2.50));
            padariaRepository.save(new Padaria(null, "Rosca Doce", 1.80));
        }
    }
}
