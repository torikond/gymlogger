package com.example.hellospring.controller;

import com.example.hellospring.domain.Member;
import com.example.hellospring.service.MemberService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final MemberService memberService;

    @Autowired //way 1 to register
    public HomeController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/")
    public String start() {
        return "start"; //returning html file name need home.html
    }


    @GetMapping("/home")
    public String home(MemberForm form, Model model) {
        //returning html file name need home.html
    Member member = new Member();
    member.setEmail("torikondratenko@gmail.com");
    memberService.findOneByEmail(member.getEmail()).ifPresent(currentMember -> model.addAttribute("currentMember", currentMember));
    return "home";

}

}


// 1. old way
// jdbc template -> still used
// JPA
