package ru.yashnov.Project2Boot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainPageController {

    @GetMapping()
    public String showMainPage() {
        return "index";
    }
}
