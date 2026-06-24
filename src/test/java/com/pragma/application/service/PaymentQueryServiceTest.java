package com.pragma.application.service;

import com.pragma.domain.model.PaymentEvent;
import com.pragma.infrastructure.repository.PaymentEventRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class PaymentQueryServiceTest {
    @Test
    public void testGetPaymentById() {
        PaymentEventRepository repository = Mockito.mock(PaymentEventRepository.class);
        PaymentQueryService service = new PaymentQueryService(repository);
        Mockito.when(repository.findById("123_channel"))
               .thenReturn(Mono.just(new PaymentEvent("123_channel", "123", "channel", "PENDING")));
        StepVerifier.create(service.getPaymentById("123_channel"))
               .expectNextMatches(event -> "123_channel".equals(event.id()))
               .verifyComplete();
    }
}