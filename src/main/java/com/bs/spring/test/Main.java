package com.bs.spring.test;

import com.bs.spring.test.model.dao.MyDao;
import com.bs.spring.test.model.dto.Person;
import com.bs.spring.test.model.service.MyService;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //spring 설정파일에서 POJO 객체 불러오기
        //spring 설정파일
        // 1. xml -> spring bean configuration
        // 2. @ -> @Configuration
        ApplicationContext context
                = new FileSystemXmlApplicationContext("/src/main/webapp/WEB-INF/spring/spring-config.xml");
        //context에 등록된 Bean 확인하기
        String[] beanNames=context.getBeanDefinitionNames();
        for(String beanName:beanNames){
            System.out.println(beanName);
        }
        //등록된 spring bean가져오기
        //클래스를 안적으면 기본적으로 object
        Person p=context.getBean("p",Person.class);
        System.out.println(p);
        Person p2=context.getBean("p2",Person.class);
        System.out.println(p2);

        ApplicationContext context2=new AnnotationConfigApplicationContext();
        Arrays.stream(context2.getBeanDefinitionNames()).forEach(beanName->{
            System.out.println(beanName);
        });
        //resistry는 등록한 것만 호출하고, bean객체값까지는 호출못한다.
        //Bean팩토리 구현체
        //SimpleBeanDefinitionRegistry registry=new SimpleBeanDefinitionRegistry();
        DefaultListableBeanFactory registry=new DefaultListableBeanFactory();

        //classPathBeanDefinitionScanner
        //Bean으로 등록된 객체를 가져온다.
        ClassPathBeanDefinitionScanner scanner=new ClassPathBeanDefinitionScanner(registry);

        //클래스패스에 특정한 객체를 정해놓고 , 어노테이션으로 가져온다.
        scanner.scan("com.bs.spring");
        System.out.println("==============@Configuration ==========");
        for(String beanName:registry.getBeanDefinitionNames()){
            System.out.println(beanName);
        };

        Arrays.stream(registry.getBeanDefinitionNames())
                .forEach(beanName->{
                    System.out.println(beanName);
                });

        MyDao dao=registry.getBean("myDao",MyDao.class);
        System.out.println(dao);
        MyService service=registry.getBean("myService",MyService.class);
        System.out.println(service);


    }
}
