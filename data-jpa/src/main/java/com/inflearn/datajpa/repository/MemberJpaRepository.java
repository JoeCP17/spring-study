package com.inflearn.datajpa.repository;

import com.inflearn.datajpa.entity.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberJpaRepository {

    // Commend + Shift + T ( 테스트 생성 )

    @PersistenceContext // 스프링 컨테이너가 JPA 영속성 컨텍스트를 통해, 데이터베이스내 접근이 가능
    private EntityManager em;

    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    public void delete(Member member) {
        em.remove(member);
    }

    public List<Member> findAll() {
         return em.createQuery("select m from Member m", Member.class).getResultList();
    }
    public Member find(Long id) {
        return em.find(Member.class, id);
    }

    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);

        return Optional.ofNullable(member);
    }

    public long count() {
        return em.createQuery("select count (m) from Member m", Long.class).getSingleResult();
    }
}
