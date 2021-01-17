package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;

    //MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
    //이렇게 생성하면 문제가 좀 있음. mmr 가보면 static으로 store가 선언되서 망정이지 아니었으면 새객체 만드는 꼴.
    //아래와 같이 수정
    MemoryMemberRepository memoryMemberRepository;

    @BeforeEach
    void beforeEach() {
        memoryMemberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryMemberRepository); //외부에서 Di(의존성 주입) 라고 한다
    }

    @AfterEach
    void afterEach() {
        memoryMemberRepository.clearStore();
    }

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
        //try -catch 사용법 말고 다른 방법이 있다
        /*try{
            memberService.join(member2);
            org.junit.jupiter.api.Assertions.fail("예외가 발생해야 합니다"); //예외가 발생해야하지만 성공해버렸을 때 발생
        }catch(IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
        }*/


        //then
    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}