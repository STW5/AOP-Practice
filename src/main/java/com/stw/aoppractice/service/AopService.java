package com.stw.aoppractice.service;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AopService {

	// 일반 외부 호출 - 프록시를 통해 호출되므로 AOP 정상 적용
	public String callExternal(String name) {
		log.info("== AopService.callExternal() 실행 ==");
		return "Hello " + name;
	}

	// self-invocation 문제 확인용
	// outer()는 AOP 적용되지만, 내부에서 inner()를 직접 호출하면 프록시를 거치지 않음
	public void outer() {
		log.info("== AopService.outer 실행 ==");
		inner(); // this.inner() → 프록시 우회 → AOP 미적용
	}

	public void inner() {
		log.info("== AopService.inner 실행 ==");
	}

}
