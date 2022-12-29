package com.inflearn.datajpa.entity;

import org.junit.jupiter.api.Test;
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
}