package com.pubsubdoc.payment.service.web.app.application.domain.models.payment;

import com.example.appkit.application.basic.EventDrivenAggregateRoot;
import com.pubsubdoc.payment.service.api.domain.models.payment.PaymentCompleted;
import com.pubsubdoc.payment.service.api.domain.models.payment.PaymentEvent;
import com.pubsubdoc.payment.service.api.domain.models.payment.PaymentFailed;
import com.pubsubdoc.payment.service.api.domain.models.payment.PaymentRequested;
import com.pubsubdoc.payment.service.shared.application.doc.models.payment.PaymentId;
import com.pubsubdoc.user.service.shared.application.doc.models.user.UserId;

import java.math.BigDecimal;

public record Payment(PaymentId paymentId, UserId userId) implements EventDrivenAggregateRoot<PaymentEvent> {
    public static PaymentRequested create(PaymentId paymentId, UserId userId, BigDecimal price) {
        return new PaymentRequested(paymentId, userId, price);
    }

    public static Payment applyEvent(PaymentRequested event) {
        return new Payment(event.paymentId(), event.userId());
    }

    @Override
    public EventDrivenAggregateRoot<PaymentEvent> applyEvent(PaymentEvent event) {
        return switch (event) {
            case PaymentRequested __ -> new Payment(paymentId, userId);
            case PaymentCompleted __ -> this;
            case PaymentFailed __ -> this;
            default -> throw new IllegalStateException("Unexpected value: " + event);
        };
    }

    public PaymentCompleted complete() {
        return new PaymentCompleted(paymentId, userId);
    }

    public PaymentFailed fail() {
        return new PaymentFailed(paymentId);
    }
}