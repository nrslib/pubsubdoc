package com.pubsubdoc.payment.service.api.domain.models.payment;

import com.pubsubdoc.payment.service.shared.application.doc.models.payment.PaymentId;

public record PaymentFailed(PaymentId paymentId) implements PaymentEvent {
}
