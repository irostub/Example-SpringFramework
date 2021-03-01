package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    //private final DataSource dataSource;
    //private EntityManager em;
    //자동으로 spring data jpa 를 주입시켜준다
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*@Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }*/

    /*public SpringConfig(DataSource d) {
        this.dataSource = d;
    }*/

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

    /*@Bean
    public MemberRepository memberRepository(){
        //MemoryMemberRepository 메모리 데이터레포 대신 이제 실제 DB Jdbc을 사용하고자 할 때, 다형성 실현
        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        //return new JdbcTemplateMemberRepository(dataSource);
        //return new JpaMemberRepository(em);
    }
    */
}
