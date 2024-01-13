package com.pubsubdoc.payment.service.api.domain.models.paymentprocess;

import com.pubsubdoc.payment.service.shared.application.doc.models.paymentprocess.PaymentMethod;
import com.pubsubdoc.payment.service.shared.application.doc.models.paymentprocess.PaymentProcessId;
import com.pubsubdoc.user.service.shared.application.doc.models.user.UserId;

import java.math.BigDecimal;

public record PaymentProcessRequested(PaymentProcessId paymentProcessId, UserId userId, BigDecimal amount, PaymentMethod paymentMethod) implements PaymentProcessEvent {
}
