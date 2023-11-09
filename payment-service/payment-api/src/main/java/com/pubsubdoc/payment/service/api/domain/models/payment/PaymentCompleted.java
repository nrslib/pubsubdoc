package com.pubsubdoc.payment.service.api.domain.models.payment;

import com.pubsubdoc.payment.service.shared.application.doc.models.payment.PaymentId;
import com.pubsubdoc.user.service.shared.application.doc.models.user.UserId;

public record PaymentCompleted(PaymentId paymentId, UserId userId) implements PaymentEvent {
}
