package com.pubsubdoc.payment.service.shared.application.doc.models.payment;

import com.example.appkit.application.basic.IdObject;

import java.util.UUID;

public record PaymentId(UUID value) implements IdObject {
    public PaymentId() {
        this(UUID.randomUUID());
    }

    @Override
    public String asString() {
        return value.toString();
    }

    @Override
    public String toString() {
        return asString();
    }
}
