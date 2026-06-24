package com.pragma.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PaymentEventTest {
    @Test
    public void testPaymentEventCreation() {
        String id = "123_channel";
        PaymentEvent event = new PaymentEvent(id, "123", "channel", "PENDING");
        Assertions.assertEquals(id, event.id());
    }
}