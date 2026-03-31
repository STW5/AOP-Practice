package com.stw.aoppractice.aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

// Spring AOP - Proxy 기반 방식
// @Aspect: 이 클래스가 AOP 로직을 담고 있음을 선언
// @Component: Spring 빈으로 등록 → Spring이 프록시를 만들어 관리
@Slf4j
@Aspect
@Component
public class DebugAspect {

	// Pointcut: service 패키지 하위의 모든 메서드 호출을 가로챔
	// @Around: 메서드 실행 전후 모두 개입 가능한 Advice 타입
	@Around("execution(* com.stw.aoppractice.service..*(..))")
	public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info("===== AOP START =====");
		log.info("method = {}", joinPoint.getSignature().toShortString());
		log.info("args = {}", Arrays.toString(joinPoint.getArgs()));
		log.info("proxy(this) = {}", joinPoint.getThis().getClass()); // CGLIB 프록시 객체
		log.info("target = {}", joinPoint.getTarget().getClass());     // 실제 대상 객체

		try {
			// 실제 메서드 실행
			Object result = joinPoint.proceed();
			log.info("result = {}", result);
			return result;
		} finally {
			// 예외가 발생해도 반드시 실행
			log.info("===== AOP END =====");
		}
	}
}
