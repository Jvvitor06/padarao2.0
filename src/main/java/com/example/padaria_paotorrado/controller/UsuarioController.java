package com.example.padaria_paotorrado.controller;

import com.example.padaria_paotorrado.Business.PadariaService;
import com.example.padaria_paotorrado.Business.UsuarioService;
import com.example.padaria_paotorrado.infrastructure.entitys.Padaria;
import com.example.padaria_paotorrado.infrastructure.entitys.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:63342")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    // Listar todos os usuários
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok(usuarios);
    }

    // Salvar novo usuário
    @PostMapping
    public ResponseEntity<Void> salvarUsuario(@RequestBody Usuario usuario) {
        usuarioService.salvarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Buscar usuário por CPF
    @GetMapping("/cpf")
    public ResponseEntity<Usuario> buscarUsuarioPorCpf(@RequestParam String cpf) {
        Usuario usuario = usuarioService.buscarPorCpf(cpf);
        return ResponseEntity.ok(usuario);
    }

    // Deletar usuário por CPF
    @DeleteMapping
    public ResponseEntity<Void> deletarUsuario(@RequestParam String cpf) {
        usuarioService.deletarPorCpf(cpf);
        return ResponseEntity.ok().build();
    }

    // Atualizar usuário por CPF
    @PutMapping
    public ResponseEntity<Void> atualizarUsuario(@RequestParam String cpf, @RequestBody Usuario usuario) {
        usuarioService.atualizarPorCpf(cpf, usuario);
        return ResponseEntity.ok().build();
    }
}