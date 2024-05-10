package com.finalproject.cafePriceAnalysis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {
	@GetMapping("/rest/home")
    public String home() {
        return "Hello, Spring Boot!";
    }
}
