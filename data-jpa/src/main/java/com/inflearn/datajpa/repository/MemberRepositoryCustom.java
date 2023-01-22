package com.inflearn.datajpa.repository;

import com.inflearn.datajpa.entity.Member;

import java.util.List;

public interface MemberRepositoryCustom {

    List<Member> findMemberCustom();
}
