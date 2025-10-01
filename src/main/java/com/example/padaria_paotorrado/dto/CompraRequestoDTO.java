package com.example.padaria_paotorrado.dto;

import lombok.Data;

import java.util.List;

@Data
public class CompraRequestoDTO {
    private String usuarioId;
    private List<String> produtosIds;
}
