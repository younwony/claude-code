package com.example.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "안녕하세요! Spring Boot 웹 애플리케이션입니다.");
        model.addAttribute("currentTime", LocalDateTime.now().format(
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        ));
        return "index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "소개");
        model.addAttribute("description", "이것은 Spring Boot와 Thymeleaf를 사용한 간단한 웹 애플리케이션입니다.");
        return "about";
    }
}
