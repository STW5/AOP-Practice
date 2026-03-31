package com.stw.aoppractice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stw.aoppractice.service.AopService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AopController {
	private final AopService aopService;

	public AopController(AopService demoService) {
		this.aopService = demoService;
	}

	// 주입된 빈이 실제 AopService가 아닌 CGLIB 프록시임을 확인
	@GetMapping("/proxy-check")
	public String proxyCheck() {
		log.info("주입된 객체 클래스 = {}", aopService.getClass());
		return "check console";
	}

	// 일반 외부 호출 - AOP 정상 적용 확인 (기준점)
	@GetMapping("/external")
	public String external(@RequestParam String name) {
		return aopService.callExternal(name);
	}

	// self-invocation 확인 - outer()는 AOP 적용, inner()는 미적용
	@GetMapping("/self")
	public String selfInvocation() {
		aopService.outer();
		return "done";
	}

	// new로 직접 생성한 객체는 Spring 컨테이너 밖 → AOP 전혀 안 걸림
	@GetMapping("/new")
	public String newObject() {
		AopService newService = new AopService();
		return newService.callExternal("new-object");
	}

}
