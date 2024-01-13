package com.pubsubdoc.payment.service.shared.application.doc.models.paymentprocess;

import com.example.appkit.application.basic.IdObject;

import java.util.UUID;

public record PaymentProcessId(UUID value) implements IdObject {
    public PaymentProcessId() {
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
