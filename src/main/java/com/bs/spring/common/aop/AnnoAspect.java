package com.bs.spring.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class AnnoAspect {
    //pointcut설정 ->@PointCut("표현식")
    //advice설정  ->@Before||@After||@Around..

    @Pointcut("execution(* com.bs.spring..*(..))")
    public void testall(){}//pointcut 지정

    //타깃이 되는 메소드가 실행하기 전의 메소드를 실행
//    @Before("testall()")
//    public void before(JoinPoint joinPoint){
//        log.debug("==== anno AOP before ====");
//        Signature sig = joinPoint.getSignature();
//        log.debug(sig.getDeclaringTypeName() + "." + sig.getName());
//        //메소드에 전달되는 매개변수 확인하기
//        Object[] args = joinPoint.getArgs();
//
//        if(args!=null && args.length>0){
//            Arrays.stream(args).forEach(d->log.debug("매개변수 : {}",d));
//        }
//        //ExceptionHandler만들어 놓고, 로그 찍고, 에러나면 Exception 띄우고, 로그를 남긴다.
//        log.debug("===============================");
//    }

    //dao에 save 메소드로 등록된 얘들 가져오기
    @Pointcut("execution(* com.bs.spring..add*(..))")
    public void daoSave(){}

//    @After("daoSave()")
//    public void after(JoinPoint joinPoint){
//        log.debug("==== anno AOP after ====");
//        Signature sig = joinPoint.getSignature();
//        log.debug(sig.getDeclaringTypeName() + "." + sig.getName());
//        Object[] args = joinPoint.getArgs();
//        if(args!=null && args.length>0){
//            Arrays.stream(args).forEach(d->log.debug("매개변수 : {}",d));
//        }
//
//        log.debug("======================================");
//    }
    //Pointcut을 직접 매개변수에 어노테이션의 매개변수로 집어넣어서 쓸수 있다.
    //@Around("execution(* com.bs.spring.member..*(..))")
    //메소드보다 큰범위에 클래스, 패키지 등으로 설정가능
    //within(com.bs.spring.common.인터페이스명+)
    // -> 인터페이스 구현체
    @Around("within(com.bs.spring..dao.*)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        //전, 후에 로직을 실행
        //전,후 로직을 메소드내부에 선언
        //시간체크하는 것
        //메소드의 내용이 메소드 전, 후가 같은지 확인
        //전 로직, 후 로직을 구분하는 기준 -> joinPoint.proceed()메소드호출로 전, 후 시점을 나눈다.
        log.debug("=== around 전 로직 ====");
        Object o = joinPoint.proceed();
        log.debug("===around 후 로직====");
        return o;
    }
    //+ 는 어노테이션이 적용된 메소드라고 보면된다.
    //@어노테이션으로 설정한 AOP는 인터페이스에서 설정할 수 있고,
    // 인터페이스구현체를 실행할때, 사용된다.
    @Before("within(com.bs.spring.board.model.service.BoardService)")
    public void before(JoinPoint joinPoint) throws Throwable {
        log.debug("==== 인터페이스를 구현한 클래스의 메소드 실행전   ====");
        Object[] args=joinPoint.getArgs();
        Arrays.stream(args).forEach(o->log.debug("매개변수 : {}",o));
    }


    @Before("@annotation(com.bs.spring.common.myannotation.MyAnnotation)")
    public void annobefore(JoinPoint joinPoint) throws Throwable {
        log.debug("==== 특정어노테이션을 선언한  메소드 실행전   ====");
        Object[] args=joinPoint.getArgs();
        Arrays.stream(args).forEach(o->log.debug("매개변수 : {}",o));
    }

    //대상메소드에서 예외가 발생했을떄,
    @AfterThrowing(value="within(com.bs.spring..controller.*)"
            , throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) throws Throwable {
        log.debug("===== 비상비상 에러발생 ===========");
        Signature sig = joinPoint.getSignature();
        log.debug(sig.getDeclaringTypeName() + "." + sig.getName());
        log.debug(e.getMessage());
        StackTraceElement[] stackTrace=e.getStackTrace();
        for(StackTraceElement stackTraceElement:stackTrace){
            log.debug(stackTraceElement.toString());
        }
    }

    @Around("within(com.bs.spring..dao.*)")
    public Object around2(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug("database접속 시간 확인 ");
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        Object obj = joinPoint.proceed();
        stopWatch.stop();
        log.debug("실행시간 : {}",stopWatch.getTotalTimeMillis()+"ms");
        return obj;
    }

}
