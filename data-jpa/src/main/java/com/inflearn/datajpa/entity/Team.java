package com.inflearn.datajpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "userName"})
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "team_id")
    private Long id;

    private String userName;

    private int age;

    @OneToMany(mappedBy = "team") // 일대 다 관계     // mappedBy는, FK가 없는쪽에 걸어두는편이 좋다.
    private List<Member_v2> members = new ArrayList<>();

    public Team(String userName) {
        this.userName = userName;
    }
}
