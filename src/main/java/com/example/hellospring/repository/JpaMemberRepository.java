package com.example.hellospring.repository;

import com.example.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    // constructor
    public JpaMemberRepository(EntityManager em){
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        //search data with primary key
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        // not primary key
        List<Member> result = em.createQuery("select m from Member m where m.email = :email", Member.class).setParameter("email", email).getResultList();

        return result.stream().findAny();
    }

    @Override
    public Optional<Member> validateLogin(String email, String password) {
        // not primary key
        List<Member> result = em.createQuery("select m from Member m where m.email = :email and m.password = :password", Member.class).setParameter("email", email).setParameter("password", password).getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();

    }
}
