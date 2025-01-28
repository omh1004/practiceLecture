package com.bs.spring.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration//==springbeanconfiguration.xml과 동일한 설정
public class BeanConfigTest {

    //Bean등록하기
    @Bean
    public Scanner scanner(){
        return new Scanner(System.in);
    }
    /*<bean id=scanner class=java.util.Scanner>
    *   <constructor-arg index=0 ref="System.in">
    *  */

}
