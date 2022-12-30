package com.inflearn.datajpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter // Setter는 가급적 사용하지 않는다.
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String userName;

    private int age;

    public Member(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    // jpa에서 proxy를 할때 private으로 적용하면 사용할 수 없기에 protected로 설정
    protected Member() {
    }

    public Member(String userName) {
        this.userName = userName;
    }


}
