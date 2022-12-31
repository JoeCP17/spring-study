package com.inflearn.datajpa.dto;

public class MemberDTO {

    private final Long id;
    private final String userName;
    private final String teamName;

    public MemberDTO(Long id, String userName, String teamName) {
        this.id = id;
        this.userName = userName;
        this.teamName = teamName;
    }
}
