package com.example.jobportal.controller;

import com.example.jobportal.model.Role;
import com.example.jobportal.service.UserService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Validated
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    public record RegisterForm(@NotBlank String name, @Email String email, @NotBlank String password, Role role) {}

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("form", new RegisterForm("", "", "", Role.APPLICANT));
        model.addAttribute("roles", Role.values());
        return "auth/register";
    }

    @PostMapping("/register")
    public String doRegister(@ModelAttribute("form") RegisterForm form) {
        userService.register(form.name(), form.email(), form.password(), form.role());
        return "redirect:/login?registered";
    }
}
