package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

//Transactional 어노테이션은 DB에서 commit 이 이루어지기 전에 rollback 한다
//그러므로 AfterEach 어노테이션을 사용하여 초기화하는 작업을 생략한다
@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given (주어진 것에서 - 준비)
        Member member = new Member();
        member.setName("spring");

        //when  (어떤 상황에 - 실행)
        Long saveId = memberService.join(member);

        //then  (이 결과를 - 검증)
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원_예외처리(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2)); //람다를 실행했을 때 해당 예외가 발생하면 성공하고 해당 예외를 반환
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");

    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}