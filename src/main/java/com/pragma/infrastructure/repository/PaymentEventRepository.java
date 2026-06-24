package com.pragma.infrastructure.repository;

import com.pragma.domain.model.PaymentEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import reactor.core.publisher.Mono;

public interface PaymentEventRepository extends JpaRepository<PaymentEvent, String> {
    Mono<PaymentEvent> findById(String id);
}