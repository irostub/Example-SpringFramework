package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    //Jpa를 사용하기 위해 EntityManager를 spring boot로부터 의존성 주입 받아야됨
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        //persist : 저장, 보존
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member m = em.find(Member.class, id);
        return Optional.ofNullable(m);
    }

    @Override
    public Optional<Member> findByName(String name) {
        //jpql을 사용해야됨
        List<Member> result = em.createQuery("select m from Member m where m.name=:name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
