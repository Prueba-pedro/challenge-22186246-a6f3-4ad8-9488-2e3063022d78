package com.pragma.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "anti-fraud-service")
public interface AntiFraudServiceClient {
    @PostMapping("/check")
    String checkFraud(@RequestParam String operationNumber);
}