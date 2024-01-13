package com.pubsubdoc.payment.service.web.http.models.payments.post;

import com.pubsubdoc.payment.service.shared.application.doc.models.paymentprocess.PaymentMethod;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentsPostRequest(UUID userId, BigDecimal amount, PaymentMethod paymentMethod) {
}
