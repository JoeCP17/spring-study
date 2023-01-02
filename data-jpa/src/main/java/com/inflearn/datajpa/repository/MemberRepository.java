package com.inflearn.datajpa.repository;

import com.inflearn.datajpa.dto.MemberDTO;
import com.inflearn.datajpa.entity.Member_v2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member_v2, Long> {

    List<Member_v2> findByUserNameAndAgeGreaterThan(String username, int age);

    @Query("select m from Member_v2 m where m.userName = :username and m.age = :age")
    List<Member_v2> findUser(@Param("username") String username, @Param("age") int age);

    @Query("select m.userName from Member_v2 m")
    List<String> findUserNameList();

    @Query("select new com.inflearn.datajpa.dto.MemberDTO(m.id, m.userName, t.name) from Member_v2 m join m.team t")
    List<MemberDTO> findMemberDTO();

    @Query("select m from Member_v2 m where m.userName in :names")
    List<Member_v2> findMember_v2ByUserName(@Param("names") Collection<String> names);


    List<Member_v2> findListByUserName(String userName); // 컬렉션
    Member_v2 findMemberByUserName(String userName); // 단건
    Optional<Member_v2> findOptionalMemberByUserName(String userName); // 단건

    @Query(value = "select m from Member_v2 m left join m.team t"
            , countQuery = "select count(m) from Member_v2 m")
    Page<Member_v2> findByAge(int age, Pageable pageable);

    // Slice는 total 계산을 해주지 않는다.
    Slice<Member_v2> findSliceByAge(int age, Pageable pageable);

    // bulk update
    @Modifying(clearAutomatically = true) // Modify가 있어야 executeUpdate를 실행한다. 없다면 SingleResult를 실행해버린다.
    // clearAutomatically = true로 하면 벌크 연산 후, 클리어 처리를 자동적으로 처리해준다.
    @Query("update Member_v2 m set m.age = m.age + 1 where m.age >=:age")
    int bulkAgePlus(@Param("age") int age);

    @Query("select m from Member_v2 m left join fetch m.team")
    List<Member_v2> findMemberFetchJoin();

    @Override
    @EntityGraph(attributePaths = {"team"}) // fetch Join 을 사용할 수 있다.
    List<Member_v2> findAll();

    @EntityGraph(attributePaths = {"team"})
    @Query("select m from Member_v2 m")
    List<Member_v2> findMemberEntityGraph();

//    @EntityGraph(attributePaths = {"team"})
//    List<Member_v2> findEntityGraphByUserName(@Param("userName") String name);

//    @EntityGraph(attributePaths = {"team"})
    @EntityGraph("Member_v2.all")
    List<Member_v2> findEntityGraphByUserName(@Param("userName") String name);

    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true")) // JPA 가 쿼리힌트를 사용할 수 있도록 Hibernate를 열어둔다.
    Member_v2 findReadOnlyByUserName(String username); // readOnly를 통해 읽기만을 하는구나 라고 생각하기에, 변경감지를 하지 않는다.
    // 해당 부분은 조회 전체가 아닌, 성능테스트 이후 정말 중요한 부분에 넣으면 이점이 된다면? -> 넣는다.
    // 사실, 그전에 캐싱처리등으로 가는게 맞지만 정말 마지막 최후의 보루로 생각하면 좋을듯..?

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Member_v2 findLockByUserName(String username);
}
