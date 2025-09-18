package com.example.padaria_paotorrado.Business;

import org.springframework.context.annotation.Configuration;
import com.example.padaria_paotorrado.infrastructure.entitys.User;
import com.example.padaria_paotorrado.infrastructure.repository.UserRepository;
import com.example.padaria_paotorrado.infrastructure.repository.role.UserRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // cria admin se não existir
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User(
                        "admin",
                        passwordEncoder.encode("123456"),
                        UserRole.ADMIN
                );
                userRepository.save(admin);
                System.out.println("✅ Usuário ADMIN criado (login: admin / senha: 123456)");
            }

            // cria usuário normal se não existir
            if (userRepository.findByUsername("joao").isEmpty()) {
                User cliente = new User(
                        "joao",
                        passwordEncoder.encode("senha123"),
                        UserRole.USER
                );
                userRepository.save(cliente);
                System.out.println("✅ Usuário USER criado (login: joao / senha: senha123)");
            }
        };
    }
}
