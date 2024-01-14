package com.pubsubdoc.payment.service.shared.application.doc.models.transferrequest;

import com.example.appkit.application.basic.IdObject;

import java.util.UUID;

public record TransferRequestId(UUID value) implements IdObject {
    public TransferRequestId() {
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
