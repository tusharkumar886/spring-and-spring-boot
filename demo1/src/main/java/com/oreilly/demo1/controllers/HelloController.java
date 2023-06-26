package com.oreilly.demo1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("/hello") // http://localhost:8080/hello?name=John
    public String sayHello(@RequestParam(defaultValue = "World") String name,
                           Model model) {
        model.addAttribute("username", name.toUpperCase());
        return "welcome"; // forward to src/main/resources/templates/welcome.html
    }
}
