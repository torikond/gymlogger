package com.example.hellospring.service;

import com.example.hellospring.domain.Member;
import com.example.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    //MemberService memberService = new MemberService();
    //TMemberRepository memberRepository = new TMemberRepository();
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

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