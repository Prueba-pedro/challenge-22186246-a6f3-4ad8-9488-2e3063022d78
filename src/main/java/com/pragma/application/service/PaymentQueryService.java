package com.pragma.application.service;

import com.pragma.domain.model.PaymentEvent;
import com.pragma.infrastructure.repository.PaymentEventRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PaymentQueryService {
    private final PaymentEventRepository repository;

    public PaymentQueryService(PaymentEventRepository repository) {
        this.repository = repository;
    }

    public Mono<PaymentEvent> getPaymentById(String id) {
        return repository.findById(id);
    }
}