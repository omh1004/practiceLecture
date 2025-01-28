package com.bs.spring.test.controller;


import com.bs.spring.test.model.dto.Animal;
import com.bs.spring.test.model.dto.Person;
import com.bs.spring.test.model.dto.Temp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Scanner;

@Controller
public class TestController {

    //의존성 등록
    //spring bean으로 등록된 객체를 가져와 사용
    //@Autowired어노테이션을 이용해서 선언
    //필드값, 매개변수 설정이 가능
    //지역변수에는 어노테이션 사용이 불가능 하다.
    @Autowired
    private Animal animal;
    @Autowired
    private Person person;

    //스프링 빈으로 등록되지 않는 클래스를 Autowired처리하면 에러
    //@Autowired했을때 옵션 설정하기
    //필수값 -> 무조건 넣어! 없으면 에러 !

    @Autowired(required = true)//있으면 넣고, 없으면 넣지마!!
    private Scanner sc;

    @Autowired
    private Temp t;

    @Autowired
    List<Animal> animals;
    //스프링이 생성하는 객체 가져오기
    @Autowired
    private WebApplicationContext wc;

    @RequestMapping("/test/beantest")
    public void testbean(){

        System.out.println("testbean실행");
        System.out.println(animal);
        System.out.println(person);
        t.printData();


        //webapplicationcontext이용
        System.out.println("====설정된 bean가져오기 ====");
        for(String name : wc.getBeanDefinitionNames()){
            System.out.println(name);
        }

        System.out.println("List 객체 가져오기 ");
        animals.forEach(System.out::println);
    }


}
