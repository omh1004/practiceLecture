package com.bs.spring.test.model.dto;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//어노테이션방식으로 클래스를 spring bean으로 등록
@Component
public class Temp {
    private Person p;
    private Animal a;
    //매개변수 있는 생성자 선언
    public Temp(@Qualifier("person") Person person, Animal animal) {
        this.p = person;
        this.a = animal;
    }

    public void printData() {
        System.out.println("person"+p);
        System.out.println("animal"+a);
    }
}
