package com.inflearn.datajpa.repository;

import com.inflearn.datajpa.dto.MemberDTO;
import com.inflearn.datajpa.entity.Member;
import com.inflearn.datajpa.entity.Member_v2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
}
