package com.inflearn.datajpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter // Setter는 가급적 사용하지 않는다.
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id","userName", "age"})
public class Member_v2 {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String userName;

    private int age;

    @ManyToOne(fetch = FetchType.LAZY) // 기본세팅은 EAGER으로 되어있는데 이를 LAZY 지연로딩으로 바꿔야한다. 즉시로딩 설정이 되어있으면 성능최적화가 매우 어렵다.
    @JoinColumn(name = "team_id")
    private Team team;

    public Member_v2(String userName) {
        this.userName = userName;
    }

    public Member_v2(String userName, int age, Team team) {
        this.userName = userName;
        this.age = age;
        if (team != null) {
            changeTeam(team);
        }
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
