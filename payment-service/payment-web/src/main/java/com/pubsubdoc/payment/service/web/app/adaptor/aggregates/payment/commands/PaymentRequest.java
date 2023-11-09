package com.pubsubdoc.payment.service.web.app.adaptor.aggregates.payment.commands;

import com.pubsubdoc.payment.service.shared.application.doc.models.payment.PaymentId;
import com.pubsubdoc.user.service.shared.application.doc.models.user.UserId;

import java.math.BigDecimal;

public record PaymentRequest(PaymentId paymentId, UserId userId, BigDecimal amount) implements PaymentCommand {
}
