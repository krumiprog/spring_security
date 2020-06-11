package com.krumiprog.blog.controllers;

import com.krumiprog.blog.models.Post;
import com.krumiprog.blog.models.User;
import com.krumiprog.blog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/read")
    public String read(Model model) {

        Iterable<Post> posts = postRepository.findAll();

        model.addAttribute("posts", posts);

        return "read";
    }

    @GetMapping("/create")
    public String create() {
        return "create";
    }

    @PostMapping("/create")
    public String create(
            @AuthenticationPrincipal User user,
            @RequestParam String title,
            @RequestParam String text,
            Model model
    ) {
        Post post = new Post(title, text, user);
        postRepository.save(post);

        return "redirect:/read";
    }
}
