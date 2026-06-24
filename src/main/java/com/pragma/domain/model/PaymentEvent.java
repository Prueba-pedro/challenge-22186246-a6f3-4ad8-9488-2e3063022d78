package com.pragma.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public record PaymentEvent(@Id String id, String operationNumber, String channel, String status) {}