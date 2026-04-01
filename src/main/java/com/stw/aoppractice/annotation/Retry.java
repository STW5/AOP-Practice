package com.stw.aoppractice.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 메서드에만 붙일 수 있는 어노테이션
@Target(ElementType.METHOD)
// 런타임에도 어노테이션 정보를 읽을 수 있도록 설정 (AOP에서 읽으려면 필수)
@Retention(RetentionPolicy.RUNTIME)
public @interface Retry {
    // 최대 재시도 횟수 (기본값 3)
    int maxAttempts() default 3;
}
