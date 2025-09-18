package com.example.padaria_paotorrado.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login"; // o Spring vai buscar login.html em /resources/templates/
    }
}