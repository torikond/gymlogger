package com.example.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String start(){
        return "start"; //returning html file name need home.html
    }


    @GetMapping("/home")
    public String home(){
        return "home"; //returning html file name need home.html
    }

}


// 1. old way
// jdbc template -> still used
// JPA
