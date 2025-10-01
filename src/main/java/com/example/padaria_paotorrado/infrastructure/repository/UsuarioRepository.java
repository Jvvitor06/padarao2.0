package com.example.padaria_paotorrado.infrastructure.repository;

import com.example.padaria_paotorrado.infrastructure.entitys.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    Optional<Usuario> findByCpf(String cpf);

    void deleteByCpf(String cpf);
}
