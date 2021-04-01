package com.example.hellospring.service;

import com.example.hellospring.domain.Member;
import com.example.hellospring.repository.TMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class MemberServiceTest {

    //MemberService memberService = new MemberService();
    //TMemberRepository memberRepository = new TMemberRepository();

    MemberService memberService;
    TMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        // memberRepository = new TMemberRepository();
        // memberService = new MemberService();

        memberService = new MemberService(memberRepository);

    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void join() {
        Member member = new Member();
        member.setFirstname("Vic");
        Long saveId = memberService.join(member);

        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getFirstname()).isEqualTo(findMember.getFirstname());
    }

    @Test
    void joinException(){
        Member member1 = new Member();
        member1.setFirstname("TK");

        Member member2 = new Member();
        member2.setFirstname("TK");

        memberService.join(member1);

        try {
            memberService.join(member2);
            fail("fail");
        }catch(IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("Name already exists");
        }
    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}