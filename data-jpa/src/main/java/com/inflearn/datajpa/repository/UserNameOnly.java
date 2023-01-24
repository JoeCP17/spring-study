package com.inflearn.datajpa.repository;

import org.springframework.beans.factory.annotation.Value;

public interface UserNameOnly {

    /*
        Entity 내부를 가져와서, 원하는 데이터를 찍어와서 계산한다.
     */
    @Value("#{target.userName + ' ' + target.age}") //Open Projection이라고 한다.
    String getUserName();
}
