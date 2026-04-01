package com.stw.aoppractice.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.stw.aoppractice.annotation.Retry;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class RetryAspect {

    // @Retry 어노테이션이 붙은 메서드만 가로챔
    @Around("@annotation(com.stw.aoppractice.annotation.Retry)")
    public Object retry(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Retry retry = signature.getMethod().getAnnotation(Retry.class);
        int maxAttempts = retry.maxAttempts();

        Exception lastException = null;

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                log.info("[Retry] {}번째 시도 - {}", attempt, signature.getName());
                Object result = joinPoint.proceed();
                log.info("[Retry] {}번째 시도 성공", attempt);
                return result;
            } catch (Exception e) {
                lastException = e;
                log.warn("[Retry] {}번째 시도 실패 - 원인: {}", attempt, e.getMessage());
            }
        }

        log.error("[Retry] 최대 재시도 횟수({}) 초과 - 최종 실패", maxAttempts);
        throw lastException;
    }
}
