package com.example.padaria_paotorrado.infrastructure.entitys;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "padarias") // nome da coleção no Mongo
public class Padaria {

    @Id
    private String id; // em Mongo o ID normalmente é String (ObjectId)

    private String nome;

    private Double preco;
}
