package com.pubsubdoc.payment.service.web.app.adaptor.aggregates.paymentprocess.commands;

import com.pubsubdoc.payment.service.shared.application.doc.models.paymentprocess.PaymentMethod;
import com.pubsubdoc.user.service.shared.application.doc.models.user.UserId;

import java.math.BigDecimal;

public record PaymentRequest(UserId userId, BigDecimal amount, PaymentMethod paymentMethod) {
}
