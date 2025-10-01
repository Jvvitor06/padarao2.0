package com.example.padaria_paotorrado.infrastructure.entitys;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "cadastros") // nome da coleção no Mongo
public class Cadastro {

    @Id
    private String id; // ObjectId no Mongo é String

    private String nome;

    private Integer idade;
}
