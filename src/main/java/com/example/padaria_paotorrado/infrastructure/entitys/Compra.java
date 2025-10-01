package com.example.padaria_paotorrado.infrastructure.entitys;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "compras") // equivalente a @Table no JPA
public class Compra {

    @Id
    private String id; // Mongo usa String (ObjectId), não Long

    @DBRef
    private Usuario usuario; // referência a outro documento

    @DBRef
    private List<Padaria> produtos; // lista de produtos

    private LocalDateTime dataCompra;

    private Double valorTotal;
}
