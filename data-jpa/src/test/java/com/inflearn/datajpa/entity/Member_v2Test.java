package com.inflearn.datajpa.entity;

import com.inflearn.datajpa.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class Member_v2Test {

    @PersistenceContext
    EntityManager em;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void testEntity() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");

        em.persist(teamA);
        em.persist(teamB);

        Member_v2 member1 = new Member_v2("member1", 10, teamA);
        Member_v2 member2 = new Member_v2("member2", 10, teamA);
        Member_v2 member3 = new Member_v2("member3", 10, teamB);
        Member_v2 member4 = new Member_v2("member4", 10, teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
        
        em.flush(); // 강제로 DB내, Insert call을 때려버림 
        em.clear(); // 캐시 초기화 
        
        //확인 
        List<Member_v2> members = em.createQuery("select m from Member_v2 m", Member_v2.class).getResultList();

        for (Member_v2 member : members) {
            System.out.println("member = " + member);
            System.out.println("-> member.team = " + member.getTeam());
        }
    }

    @Test
    public void JpaEventBaseEntity() throws InterruptedException {
        //given
        Member_v2 member = new Member_v2("member1");
        memberRepository.save(member);

        Thread.sleep(100);
        member.setUserName("member2");

        em.flush();
        em.clear();
        //when
        Member_v2 findMember = memberRepository.findById(member.getId()).get();
        //then
        System.out.println("findMember.getCreatedDate = " + findMember.getCreatedDate());
        System.out.println("findMember.getUpdatedDate = " + findMember.getLastModifiedDate());
        System.out.println("findMember.getCreatedBy = " + findMember.getCreatedBy());
        System.out.println("findMember.getModifiedBy = " + findMember.getLastModifiedBy());
    }

}