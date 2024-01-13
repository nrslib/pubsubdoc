package com.pubsubdoc.payment.service.api.domain.models.paymentprocess;

import com.pubsubdoc.payment.service.shared.application.doc.models.paymentprocess.PaymentProcessId;
import com.pubsubdoc.user.service.shared.application.doc.models.user.UserId;

public record PaymentProcessCompleted(PaymentProcessId paymentProcessId, UserId userId) implements PaymentProcessEvent {
}
