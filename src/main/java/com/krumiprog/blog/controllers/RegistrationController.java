package com.krumiprog.blog.controllers;

import com.krumiprog.blog.models.Role;
import com.krumiprog.blog.models.User;
import com.krumiprog.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String register() {

        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {

        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.addAttribute("message", "User exist");
            return "registration";
        }

        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);

        return "redirect:/";
    }
}
