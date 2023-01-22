package com.inflearn.datajpa.repository;

import com.inflearn.datajpa.dto.MemberDTO;
import com.inflearn.datajpa.entity.Member;
import com.inflearn.datajpa.entity.Member_v2;
import com.inflearn.datajpa.entity.Team;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    TeamRepository teamRepository;

    @PersistenceContext
    EntityManager entityManager;


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

    @Test
    public void testQuery() {
        Member_v2 member1 = new Member_v2("AAA", 10);
        Member_v2 member2 = new Member_v2("BBB", 20);

        memberRepository.save(member1);
        memberRepository.save(member2);

        // Jpa를 사용할때 2개 이상 넘어가면 JPQL을 통해 직접 작성
        List<Member_v2> result = memberRepository.findUser("AAA", 10);

        assertThat(result.get(0)).isEqualTo(member1);
    }

    @Test
    public void findUsernameList() {
        Member_v2 member1 = new Member_v2("AAA", 10);
        Member_v2 member2 = new Member_v2("BBB", 20);

        memberRepository.save(member1);
        memberRepository.save(member2);

        List<String> userNameList = memberRepository.findUserNameList();
        for(String s : userNameList)
            System.out.println("s = " + s);
    }

    @Test
    public void findMemberDTO() {
        Team team = new Team("teamA");
        teamRepository.save(team);

        Member_v2 member1 = new Member_v2("AAA", 10);
        memberRepository.save(member1);

        member1.setTeam(team);
        memberRepository.save(member1);

        List<MemberDTO> memberDTO = memberRepository.findMemberDTO();

        for (MemberDTO member : memberDTO) {
            System.out.println("dto" + member);
        }
    }

    @Test
    public void findByNames() {
        Team team = new Team("teamA");
        teamRepository.save(team);

        Member_v2 member1 = new Member_v2("AAA", 10);
        memberRepository.save(member1);

        member1.setTeam(team);
        memberRepository.save(member1);

        List<Member_v2> memberDTO = memberRepository.findMember_v2ByUserName(Arrays.asList("AAA", "BBB"));

        for (Member_v2 member : memberDTO) {
            System.out.println("dto" + member);
        }
    }

    @Test
    public void returnType() {
        Member_v2 member1 = new Member_v2("AAA", 10);
        Member_v2 member2 = new Member_v2("BBB", 20);

        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member_v2> findMember = memberRepository.findListByUserName("AAA");
        System.out.println("findMember = " + findMember);

        // 데이터가 확실하게 있는지 없을지 모를때는 if문을 써서 하는건 안좋은코드
        // Optional을 통한 존재 유무 판별하는게 훨씬 괜찮다.
        Optional<Member_v2> optionalMember = memberRepository.findOptionalMemberByUserName("AAA");
        System.out.println("optionalMember = " + optionalMember);
    }


    @Test
    public void Paging() {
        memberRepository.save(new Member_v2("member1", 10));
        memberRepository.save(new Member_v2("member2", 10));
        memberRepository.save(new Member_v2("member3", 10));
        memberRepository.save(new Member_v2("member4", 10));
        memberRepository.save(new Member_v2("member5", 10));

        // page는 0부터 시작
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "userName"));

        int age = 10;

        //when
        Page<Member_v2> page = memberRepository.findByAge(age, pageRequest);

        Page<MemberDTO> toMap = page.map(member_v2 -> new MemberDTO(member_v2.getId(), member_v2.getUserName(), null));

        // then
        List<Member_v2> content = page.getContent();
        long totalElements = page.getTotalElements();

        for (Member_v2 member : content) {
            System.out.println("member = " + member);
        }
        assertThat(page.getSize()).isEqualTo(3);
        assertThat(page.getTotalElements()).isEqualTo(5);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.getTotalPages()).isEqualTo(2);
        assertThat(page.isFirst()).isTrue();
        assertThat(page.hasNext()).isTrue();

        /*
            Total Count 자체가 전체 데이터 Count를 해줘야하기에, Page에서 제공하는 Total Count 자체 성능은 느리다.
            -> 따로 필터링이 되어있지 않는한, 해당 데이터를 그저 카운트만 해줘도 상관없기에 Join이 불필요 그럴땐 쿼리를 분리하여 설정해줘야함
            -> Jpa Page 쿼리에서 Join이 많이 엮여있으면 Count할때도 같은 쿼리를 그대로 사용하려 하기에 성능이 느려짐
         */
    }

    @Test
    public void bulkUpdate() {
        memberRepository.save(new Member_v2("member1", 10));
        memberRepository.save(new Member_v2("member2", 19));
        memberRepository.save(new Member_v2("member3", 20));
        memberRepository.save(new Member_v2("member4", 21));
        memberRepository.save(new Member_v2("member5", 40));

        //when
        int resultCount = memberRepository.bulkAgePlus(20);
        entityManager.flush(); // 변경되지 않은 부분들이 반영이된다.
        entityManager.clear(); // 영속성 컨텍스트 안의 데이터가 완전하게 지워짐

        List<Member_v2> result = memberRepository.findListByUserName("member5");

        //then
        assertThat(resultCount).isEqualTo(3);
    }

    @Test
    public void findMemberLazy() {
        //given
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");

        teamRepository.save(teamA);
        teamRepository.save(teamB);

        Member_v2 member1 = new Member_v2("member1", 10, teamA);
        Member_v2 member2 = new Member_v2("member2", 10, teamB);

        memberRepository.save(member1);
        memberRepository.save(member2);

        entityManager.flush();
        entityManager.clear();

        //when N + 1
        List<Member_v2> members = memberRepository.findEntityGraphByUserName("member1");

        for (Member_v2 member : members) {
            System.out.println("member = " + member.getUserName());
            System.out.println("member.teamclass = " + member.getTeam().getClass());
            System.out.println("member team = " + member.getTeam().getName());
        }
    }

    @Test
    public void queryHint() {

        Member_v2 member1 = new Member_v2("member1", 10);
        // given
        memberRepository.save(member1);

        // 영속성 컨텍스트 캐시 제거
        entityManager.flush();
        entityManager.clear();

        //When
        Member_v2 findMember = memberRepository.findReadOnlyByUserName("member1");
        findMember.setUserName("member2");

        // 더티 체킹 이후 변경을 감지하여 수정을한다.

        /*
            하지만 , 데이터 체킹 혹은 변경 데이터에 대한 탐색등
         */
        entityManager.flush();

    }
    @Test
    public void lock() {

        Member_v2 member1 = new Member_v2("member1", 10);
        // given
        memberRepository.save(member1);

        // 영속성 컨텍스트 캐시 제거
        entityManager.flush();
        entityManager.clear();

        //When
        Member_v2 findMember = memberRepository.findLockByUserName("member1");
        findMember.setUserName("member2");

        // 더티 체킹 이후 변경을 감지하여 수정을한다.

        /*
            하지만 , 데이터 체킹 혹은 변경 데이터에 대한 탐색등
         */
        entityManager.flush();
    }

    @Test
    public void callCustom() {
        List<Member> result = memberRepository.findMemberCustom();
    }

}