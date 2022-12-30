package com.inflearn.datajpa.repository;

import com.inflearn.datajpa.entity.Member;
import com.inflearn.datajpa.entity.Member_v2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("Spring JPA 테스트")
    public void testMember() {
        Member_v2 member = new Member_v2("memberA");
        Member_v2 savedMember = memberRepository.save(member);

        Member_v2 findMember = memberRepository.findById(savedMember.getId()).get();

        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUserName()).isEqualTo(member.getUserName());
        Assertions.assertThat(findMember).isEqualTo(member);
    }

    @Test
    public void findByUsernameAndAgeGreaterThen() {
        Member_v2 member1 = new Member_v2("AAA", 10);
        Member_v2 member2 = new Member_v2("BBB", 20);

        memberRepository.save(member1);
        memberRepository.save(member2);

        // Jpa를 사용할때 2개 이상 넘어가면 JPQL을 통해 직접 작성
        List<Member_v2> result = memberRepository.findByUserNameAndAgeGreaterThan("AAA", 10);

        assertThat(result.get(0).getUserName()).isEqualTo("AAA");
        assertThat(result.get(0).getAge()).isEqualTo(20);
        assertThat(result.size()).isEqualTo(1);
    }

}