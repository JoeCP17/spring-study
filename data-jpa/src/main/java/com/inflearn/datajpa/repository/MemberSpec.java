package com.inflearn.datajpa.repository;

import com.inflearn.datajpa.entity.Member_v2;
import com.inflearn.datajpa.entity.Team;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;

public class MemberSpec {

    public static Specification<Member_v2> teamName(final String teamName) {
        return new Specification<Member_v2>() {

            @Override
            public Predicate toPredicate(Root<Member_v2> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                if (StringUtils.isEmpty(teamName)) {
                    return null;
                }

                Join<Member_v2, Team> t = root.join("team", JoinType.INNER);// 회원과 Join
                return criteriaBuilder.equal(t.get("name"), teamName);
            }
        };
    }

    public static Specification<Member_v2> userName(final String userName) {
        return (Specification<Member_v2>) (root, query, builder) ->
            builder.equal(root.get("userName"), userName);
    }
}
