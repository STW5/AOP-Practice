package com.stw.aoppractice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stw.aoppractice.service.RetryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RetryController {

    private final RetryService retryService;

    public RetryController(RetryService retryService) {
        this.retryService = retryService;
    }

    // 여러 번 호출해서 성공/실패 케이스 모두 확인
    @GetMapping("/retry")
    public String retry() {
        return retryService.callApi();
    }
}
