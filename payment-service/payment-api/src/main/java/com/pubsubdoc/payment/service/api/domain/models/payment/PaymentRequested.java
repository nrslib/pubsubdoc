package com.pubsubdoc.payment.service.api.domain.models.payment;

import com.pubsubdoc.payment.service.shared.application.doc.models.payment.PaymentId;
import com.pubsubdoc.user.service.shared.application.doc.models.user.UserId;

import java.math.BigDecimal;

public record PaymentRequested(PaymentId paymentId, UserId userId, BigDecimal amount) implements PaymentEvent {
}
