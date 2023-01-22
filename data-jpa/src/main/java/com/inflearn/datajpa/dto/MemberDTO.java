package com.inflearn.datajpa.dto;

import com.inflearn.datajpa.entity.Member;
import com.inflearn.datajpa.entity.Member_v2;

public class MemberDTO {

    private final Long id;
    private final String userName;
    private final String teamName;

    public MemberDTO(Long id, String userName, String teamName) {
        this.id = id;
        this.userName = userName;
        this.teamName = teamName;
    }

    // DTO는 Entity를 봐도 괜찮다.
    public MemberDTO(Member_v2 member) {
        this.id = member.getId();
        this.userName = member.getUserName();
        this.teamName = member.getTeam().getName();
    }
}
