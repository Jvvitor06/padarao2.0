package com.example.padaria_paotorrado.infrastructure.repository;

import com.example.padaria_paotorrado.infrastructure.entitys.Cadastro;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CadastroRepositorio extends MongoRepository<Cadastro, String> {
}
