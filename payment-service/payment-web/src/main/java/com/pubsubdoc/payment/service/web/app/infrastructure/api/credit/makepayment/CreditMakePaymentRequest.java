package com.pubsubdoc.payment.service.web.app.infrastructure.api.credit.makepayment;

import com.pubsubdoc.user.service.shared.application.doc.models.user.UserId;

import java.math.BigDecimal;

public record CreditMakePaymentRequest(UserId userId, BigDecimal amount) {
}
