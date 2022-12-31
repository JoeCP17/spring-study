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

    public List<Member> findByUsernameAndAgeGreaterThen(String username, int age) {
        return em.createQuery("select m from Member m where m.userName = :userName and m.age > :age")
                .setParameter("userName", username)
                .setParameter("age", age)
                .getResultList();
    }

    public List<Member> findByPage(int age, int offset, int limit) {
        return em.createQuery("select m from Member m where m.age = :age order by m.userName desc")
                .setParameter("age", age)
                .setFirstResult(offset) // 어디서부터 가져올것인지?
                .setMaxResults(limit) // 갯수를 몇개를 가져올건지?
                .getResultList();
    }

    public long totalCount(int age) {
        return em.createQuery("select count(m) from Member  m where m.age = :age", Long.class)
                .setParameter("age", age)
                .getSingleResult();
    }

    public int bulkAgePlus(int age) {
        return em.createQuery("update Member m set m.age = m.age + 1 where m.age >= :age")
                .setParameter("age", age)
                .executeUpdate();
    }
}
