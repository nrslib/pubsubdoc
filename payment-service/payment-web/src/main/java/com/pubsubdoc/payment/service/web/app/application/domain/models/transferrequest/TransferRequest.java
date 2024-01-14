package com.pubsubdoc.payment.service.web.app.application.domain.models.transferrequest;

import com.example.appkit.application.basic.EventDrivenAggregateRoot;
import com.pubsubdoc.payment.service.api.domain.models.transferrequest.TransferRequestEvent;
import com.pubsubdoc.payment.service.api.domain.models.transferrequest.TransferRequestStarted;
import com.pubsubdoc.payment.service.shared.application.doc.models.transferrequest.TransferRequestId;

public record TransferRequest(TransferRequestId transferRequestId) implements EventDrivenAggregateRoot<TransferRequestEvent> {
    public static TransferRequestStarted create(TransferRequestId transferRequestId) {
        return new TransferRequestStarted(transferRequestId);
    }

    public static TransferRequest applyEvent(TransferRequestStarted event) {
        return new TransferRequest(event.transferRequestId());
    }

    @Override
    public EventDrivenAggregateRoot<TransferRequestEvent> applyEvent(TransferRequestEvent transferRequestEvent) {
        return switch (transferRequestEvent) {
            case TransferRequestStarted __ -> this;
            default -> throw new IllegalArgumentException("Unknown event: " + transferRequestEvent);
        };
    }
}
