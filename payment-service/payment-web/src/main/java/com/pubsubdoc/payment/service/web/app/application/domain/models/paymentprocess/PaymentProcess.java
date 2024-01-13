package com.pubsubdoc.payment.service.web.app.application.domain.models.paymentprocess;

import com.example.appkit.application.basic.EventDrivenAggregateRoot;
import com.pubsubdoc.payment.service.api.domain.models.paymentprocess.PaymentProcessCompleted;
import com.pubsubdoc.payment.service.api.domain.models.paymentprocess.PaymentProcessEvent;
import com.pubsubdoc.payment.service.api.domain.models.paymentprocess.PaymentProcessFailed;
import com.pubsubdoc.payment.service.api.domain.models.paymentprocess.PaymentProcessRequested;
import com.pubsubdoc.payment.service.shared.application.doc.models.paymentprocess.PaymentMethod;
import com.pubsubdoc.payment.service.shared.application.doc.models.paymentprocess.PaymentProcessId;
import com.pubsubdoc.user.service.shared.application.doc.models.user.UserId;

import java.math.BigDecimal;

public record PaymentProcess(PaymentProcessId paymentProcessId, UserId userId) implements EventDrivenAggregateRoot<PaymentProcessEvent> {
    public static PaymentProcessRequested create(PaymentProcessId paymentProcessId, UserId userId, BigDecimal price, PaymentMethod paymentMethod) {
        return new PaymentProcessRequested(paymentProcessId, userId, price, paymentMethod);
    }

    public static PaymentProcess applyEvent(PaymentProcessRequested event) {
        return new PaymentProcess(event.paymentProcessId(), event.userId());
    }

    @Override
    public EventDrivenAggregateRoot<PaymentProcessEvent> applyEvent(PaymentProcessEvent event) {
        return switch (event) {
            case PaymentProcessRequested __ -> new PaymentProcess(paymentProcessId, userId);
            case PaymentProcessCompleted __ -> this;
            case PaymentProcessFailed __ -> this;
            default -> throw new IllegalStateException("Unexpected value: " + event);
        };
    }

    public PaymentProcessCompleted complete() {
        return new PaymentProcessCompleted(paymentProcessId, userId);
    }

    public PaymentProcessFailed fail() {
        return new PaymentProcessFailed(paymentProcessId);
    }
}