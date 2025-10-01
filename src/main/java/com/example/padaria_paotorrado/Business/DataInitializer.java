package com.example.padaria_paotorrado.Business;

import org.springframework.context.annotation.Configuration;
import com.example.padaria_paotorrado.infrastructure.entitys.User;
import com.example.padaria_paotorrado.infrastructure.repository.UserRepository;
import com.example.padaria_paotorrado.infrastructure.repository.role.UserRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Configuration
@Component("padariaDataInitializer")
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Cria admin se não existir
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User(
                        null, // id nulo, será gerado pelo JPA
                        "admin",
                        passwordEncoder.encode("123456"),
                        UserRole.ADMIN
                );
                userRepository.save(admin);
                System.out.println("✅ Usuário ADMIN criado (login: admin / senha: 123456)");
            }

            // Cria usuário normal se não existir
            if (userRepository.findByUsername("david").isEmpty()) {
                User cliente = new User(
                        null, // id nulo, será gerado pelo JPA
                        "joao",
                        passwordEncoder.encode("1234"),
                        UserRole.USER
                );
                userRepository.save(cliente);
                System.out.println("✅ Usuário USER criado (login: david / senha: 1234)");
            }
        };
    }
}
