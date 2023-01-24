package com.inflearn.datajpa.entity;

import com.inflearn.datajpa.dto.UserNameOnlyDTO;
import com.inflearn.datajpa.repository.MemberRepository;
import com.inflearn.datajpa.repository.MemberSpec;
import com.inflearn.datajpa.repository.NestedClosedProjections;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

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

    @Test
    public void specBasic() {
        //given
        Team teamA = new Team("teamA");
        em.persist(teamA);

        Member_v2 member1 = new Member_v2("m1", 0, teamA);
        Member_v2 member2 = new Member_v2("m2", 0, teamA);
        em.persist(member1);
        em.persist(member2);

        em.flush();
        em.clear();

        //when
        Specification<Member_v2> spec = MemberSpec.userName("m1").and(MemberSpec.teamName("teamA"));
        List<Member_v2> result = memberRepository.findAll((Sort) spec);

        Assertions.assertThat(result.size()).isEqualTo(1);
    }

    @Test
    public void queryByExample() {
        //when
        // Probe : 필드에 데이터가 있는 실제 도메인 객체
        Member_v2 member = new Member_v2("m1");
        Team team = new Team("teamA");
        member.setTeam(team);

        // 특정 필드를 일치시키는 상세한 정보 제공, 재사용이 가능하다.
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("age");

        // Probe와 Matcher로 구성된다. 쿼리를 생성하는데 사용한다.
        Example<Member_v2> example = Example.of(member, matcher);// Entity 자체가 검색 대상이된다.

        List<Member_v2> members = memberRepository.findAll(example);

        Assertions.assertThat(members.get(0).getUserName()).isEqualTo("m1");

        /*
            해당 기술의 문제점은 Join으로 인한 해결이 전부 다 되어지지 않는다.
            Join이 되어지긴 하지만 inner만 가능하고 left, Outer Join은 사용할 수 없다.
         */
    }

    @Test
    public void projections() {
        //given
        Team teamA = new Team("teamA");
        em.persist(teamA);

        Member_v2 member1 = new Member_v2("m1", 0, teamA);
        Member_v2 member2 = new Member_v2("m2", 0, teamA);
        em.persist(member1);
        em.persist(member2);

        em.flush();
        em.clear();

        // when
        List<NestedClosedProjections> result = memberRepository.findProjectionsByUserName("m1", NestedClosedProjections.class);

        for (NestedClosedProjections userNameOnly : result) {
            String userName = userNameOnly.getUserName();
            System.out.println("userName = " + userName);

            String teamName = userNameOnly.getTeam().getName();
            System.out.println("teamName = " + teamName);
        }

        /*
            엔티티 하나를 넘어서 사용하는 순간 사용하기 애매하다는 단점이 있다.
         */
    }
}