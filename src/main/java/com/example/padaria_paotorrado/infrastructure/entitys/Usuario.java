package com.example.padaria_paotorrado.infrastructure.entitys;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "usuarios") // equivalente à tabela "tb_usuario"
public class Usuario {

    @Id
    private String id; // Mongo usa ObjectId (String)

    private String nome;
    private String cpf;
    private String dataNascimento;
    private String email;
    private String telefone;
}
