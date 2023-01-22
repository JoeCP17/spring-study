package com.inflearn.datajpa.repository;

import com.inflearn.datajpa.entity.Member;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom{
 // 인터페이스의 구현체를 적을떄 끝에 항상 Impl을 적어줘야한다.
    // 이는 스프링에서 정한 관례라, 해당 Impl을 스프링에서 찾아준다.


    private final EntityManager em;

    @Override
    public List<Member> findMemberCustom() {
        return em.createQuery("select m from Member m")
                .getResultList();
    }
}
