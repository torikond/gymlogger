package com.example.hellospring.service;

import com.example.hellospring.domain.Member;
import com.example.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// Service will be registered in springboot container, use autowired, so registered object can be resed
// @Service way 1 to register -> component scanner
@Transactional
public class MemberService {

    //private final TMemberRepository memberRepository = new TMemberRepository();
    private final MemberRepository memberRepository;

    // inject
    @Autowired // way 1 to register
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public Long join(Member member){

        // if returns, name is already used, can't use name
        validateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateMember(Member member){
        Optional<Member> result = memberRepository.findByEmail(member.getEmail());
        result.ifPresent(m -> {throw new IllegalStateException("Email is already taken");});
    }

    public boolean validateLogin(Member member){
        Optional<Member> result = memberRepository.validateLogin(member.getEmail(), member.getPassword());
        if (result.isPresent())
        {

            return true;
        }
        else
            return false;

    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

    public Optional<Member> findOneByEmail(String email){
        return memberRepository.findByEmail(email);
    }


}
