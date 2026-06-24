package com.pragma.application.service;

import com.pragma.domain.model.PaymentEvent;
import com.pragma.infrastructure.repository.PaymentEventRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PaymentCommandService {
    private final PaymentEventRepository repository;

    public PaymentCommandService(PaymentEventRepository repository) {
        this.repository = repository;
    }

    public Mono<PaymentEvent> processPayment(String operationNumber, String channel) {
        String id = operationNumber + "_" + channel;
        PaymentEvent event = new PaymentEvent(id, operationNumber, channel, "PENDING");
        return repository.save(event);
    }
}