package com.example.padaria_paotorrado.controller;

import com.example.padaria_paotorrado.Business.CadastroService;
import com.example.padaria_paotorrado.infrastructure.entitys.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cadastro")
public class CadastroController {

    private final CadastroService cadastroService;

    public CadastroController(CadastroService cadastroService) {
        this.cadastroService = cadastroService;
    }

    @PostMapping
    public ResponseEntity<?> cadastrarUsuario(@RequestBody User user) {
        try {
            User novoUsuario = cadastroService.cadastrarUsuario(user);
            return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao cadastrar usuário", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
