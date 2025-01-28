package com.bs.spring.test.config;

import com.bs.spring.test.model.dto.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {
    @Bean
    public Person p3(){
        return Person.builder().name("오민현").age(20).address("서울시 금천구").build();
    }
}
