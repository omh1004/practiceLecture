package com.bs.spring.common.myannotation;

import java.lang.annotation.*;

//어노테이션을 선언할 수 있는 위치
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)//생명주기
@Documented
public @interface MyAnnotation {
}
