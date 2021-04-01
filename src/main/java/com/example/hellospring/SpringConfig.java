package com.example.hellospring;

import com.example.hellospring.repository.JpaMemberRepository;
import com.example.hellospring.repository.MemberRepository;
import com.example.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

//way 2 to register objects in container

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    private EntityManager em;

    //jbdc
    //@Autowired
    //public SpringConfig(DataSource dataSource){this.dataSource = dataSource;}

    @Autowired
    public SpringConfig(EntityManager em){this.em = em;}

    @Bean //bean means object
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    // easy to change the repo by just returning a dif member repo that uses member repo interface
    @Bean
    public MemberRepository memberRepository(){
        return new JpaMemberRepository(em);
    }

    // Solid design principles
    // SRP (one responsibility, OCP -> open closed principle -> open extension and closed for modification
}
