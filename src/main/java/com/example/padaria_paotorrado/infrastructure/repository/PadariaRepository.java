package com.example.padaria_paotorrado.infrastructure.repository;

import com.example.padaria_paotorrado.infrastructure.entitys.Padaria;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PadariaRepository extends MongoRepository<Padaria, String> {

    Optional<Padaria> findById(String id);

    void deleteById(String id);
}
