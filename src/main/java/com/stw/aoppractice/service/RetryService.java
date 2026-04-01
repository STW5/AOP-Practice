package com.stw.aoppractice.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.stw.aoppractice.annotation.Retry;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RetryService {

    private final Random random = new Random();

    // 최대 3번 재시도
    // 70% 확률로 실패하는 외부 API 호출 흉내
    @Retry(maxAttempts = 3)
    public String callApi() {
        log.info("[RetryService] 외부 API 호출 중...");

        if (random.nextInt(10) < 7) {
            throw new RuntimeException("외부 API 호출 실패");
        }

        return "API 호출 성공";
    }
}
