package com.inflearn.datajpa.repository;

import com.inflearn.datajpa.entity.Member;
import com.inflearn.datajpa.entity.Member_v2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member_v2, Long> {

    List<Member_v2> findByUserNameAndAgeGreaterThan(String username, int age);
}
