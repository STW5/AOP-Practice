package com.stw.aoppractice.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import lombok.extern.slf4j.Slf4j;

// 바이트코드 방식 (AspectJ LTW - Load-Time Weaving)
// @Component 없음 - Spring 컨테이너가 아닌 AspectJ가 직접 관리
// 클래스 로드 시점에 바이트코드를 직접 조작하므로 프록시 방식의 한계를 극복
@Slf4j
@Aspect
public class LtwAspect {

    // self-invocation 문제 확인용
    // 프록시 방식(DebugAspect)은 inner()를 잡지 못하지만 LTW는 잡을 수 있음
    @Around("execution(* com.stw.aoppractice.service.AopService.inner())")
    public Object traceInner(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[LTW] ===== inner() AOP START - 바이트코드 방식 =====");
        try {
            return joinPoint.proceed();
        } finally {
            log.info("[LTW] ===== inner() AOP END =====");
        }
    }
}
