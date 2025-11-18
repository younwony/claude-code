package com.example.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class HomeController {

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // View names
    private static final String VIEW_INDEX = "index";
    private static final String VIEW_ABOUT = "about";

    // Model attribute names
    private static final String ATTR_MESSAGE = "message";
    private static final String ATTR_CURRENT_TIME = "currentTime";
    private static final String ATTR_TITLE = "title";
    private static final String ATTR_DESCRIPTION = "description";

    // Message constants
    private static final String WELCOME_MESSAGE = "안녕하세요! Spring Boot 웹 애플리케이션입니다.";
    private static final String ABOUT_TITLE = "소개";
    private static final String ABOUT_DESCRIPTION = "이것은 Spring Boot와 Thymeleaf를 사용한 간단한 웹 애플리케이션입니다.";

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute(ATTR_MESSAGE, WELCOME_MESSAGE);
        model.addAttribute(ATTR_CURRENT_TIME, getCurrentFormattedTime());
        return VIEW_INDEX;
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute(ATTR_TITLE, ABOUT_TITLE);
        model.addAttribute(ATTR_DESCRIPTION, ABOUT_DESCRIPTION);
        return VIEW_ABOUT;
    }

    private String getCurrentFormattedTime() {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER);
    }
}
