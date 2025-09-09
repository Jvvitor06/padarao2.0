package com.example.padaria_paotorrado.Business;

import com.example.padaria_paotorrado.infrastructure.entitys.User;
import com.example.padaria_paotorrado.infrastructure.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CadastroService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CadastroService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User cadastrarUsuario(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Usuário já cadastrado!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
