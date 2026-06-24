package com.pragma.application.service;

import com.pragma.domain.model.PaymentEvent;
import com.pragma.infrastructure.repository.PaymentEventRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class PaymentCommandServiceTest {
    @Test
    public void testProcessPayment() {
        PaymentEventRepository repository = Mockito.mock(PaymentEventRepository.class);
        PaymentCommandService service = new PaymentCommandService(repository);
        Mockito.when(repository.save(Mockito.any(PaymentEvent.class)))
               .thenReturn(Mono.just(new PaymentEvent("123_channel", "123", "channel", "PENDING")));
        StepVerifier.create(service.processPayment("123", "channel"))
               .expectNextMatches(event -> "123_channel".equals(event.id()))
               .verifyComplete();
    }
}