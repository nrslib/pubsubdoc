package com.pubsubdoc.payment.service.web.app.application.domain.models.paymentprocess;

import com.example.appkit.application.basic.EventDrivenAggregateRoot;
import com.pubsubdoc.payment.service.api.domain.models.paymentprocess.PaymentProcessCompleted;
import com.pubsubdoc.payment.service.api.domain.models.paymentprocess.PaymentProcessEvent;
import com.pubsubdoc.payment.service.api.domain.models.paymentprocess.PaymentProcessFailed;
import com.pubsubdoc.payment.service.api.domain.models.paymentprocess.PaymentProcessRequested;
import com.pubsubdoc.payment.service.shared.application.doc.models.paymentprocess.PaymentMethod;
import com.pubsubdoc.payment.service.shared.application.doc.models.paymentprocess.PaymentProcessId;
import com.pubsubdoc.payment.service.web.app.application.domain.models.paymentprocess.errors.PaymentProcessError;
import com.pubsubdoc.payment.service.web.app.application.domain.models.paymentprocess.errors.PaymentProcessInvalidError;
import com.pubsubdoc.user.service.shared.application.doc.models.user.UserId;
import fj.data.Either;

import java.math.BigDecimal;

public record PaymentProcess(PaymentProcessId paymentProcessId, UserId userId, boolean error) implements EventDrivenAggregateRoot<PaymentProcessEvent> {
    public static PaymentProcessRequested create(PaymentProcessId paymentProcessId, UserId userId, BigDecimal price, PaymentMethod paymentMethod) {
        return new PaymentProcessRequested(paymentProcessId, userId, price, paymentMethod);
    }

    public static PaymentProcess applyEvent(PaymentProcessRequested event) {
        return new PaymentProcess(event.paymentProcessId(), event.userId(), false);
    }

    @Override
    public EventDrivenAggregateRoot<PaymentProcessEvent> applyEvent(PaymentProcessEvent event) {
        return switch (event) {
            case PaymentProcessRequested __ -> new PaymentProcess(paymentProcessId, userId, false);
            case PaymentProcessCompleted __ -> this;
            case PaymentProcessFailed __ -> new PaymentProcess(paymentProcessId, userId, true);
            default -> throw new IllegalStateException("Unexpected value: " + event);
        };
    }

    public Either<PaymentProcessError, PaymentProcessCompleted> complete() {
        if (error) {
            return Either.left(new PaymentProcessInvalidError());
        }

        return Either.right(new PaymentProcessCompleted(paymentProcessId, userId));
    }

    public PaymentProcessFailed fail() {
        return new PaymentProcessFailed(paymentProcessId);
    }
}