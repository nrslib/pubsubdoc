package com.pubsubdoc.payment.service.web.app.external.credit.protocol.sendpaymentexecution;

import com.pubsubdoc.payment.service.shared.application.doc.models.payment.PaymentId;
import com.pubsubdoc.user.service.shared.application.doc.models.user.UserId;

import java.math.BigDecimal;

public record CreditApply(PaymentId paymentId, UserId userId, BigDecimal amount) {
}
