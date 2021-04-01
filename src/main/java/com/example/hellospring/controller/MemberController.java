package com.example.hellospring.controller;

import com.example.hellospring.domain.Member;
import com.example.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class MemberController {

    // @Autowired (field injection)
    private final MemberService memberService;

    // constructor injection (other kinds: field injection, setter injection)
    // constructor recommended
    @Autowired //way 1 to register
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/register/new")
    public String createAccount() {
        return "members/register"; //direction / html page
        //name passed to /members/new -> use post method (from html)
    }

    @PostMapping("/register/new")
    public String create(MemberForm form) {
        //to send data to server, need MemberForm
        //called at submit button from createMember
        Member member = new Member();
        member.setFirstname(form.getFirstname());
        member.setLastname(form.getLastname());
        member.setEmail(form.getEmail());
        member.setPassword(form.getPassword());

        memberService.join(member);
        //when click submit want it to go to previous page
        return "redirect:/";


    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        // need key to access data which is ""
        return "members/memberList";
    }

    @GetMapping("/login")
    public String login() {
        return "members/login";
    }

    @PostMapping("/login")
    public String checkLogin(MemberForm form, Model model) {
        Member member = new Member();
        member.setEmail(form.getEmail());
        member.setPassword(form.getPassword());

        if(memberService.validateLogin(member)) {
            memberService.findOneByEmail(member.getEmail()).ifPresent(currentMember -> model.addAttribute("currentMember", currentMember));
            return "/home";
        }
        else {
            model.addAttribute("loginError", true);
            return "members/login";
        }

    }
}
