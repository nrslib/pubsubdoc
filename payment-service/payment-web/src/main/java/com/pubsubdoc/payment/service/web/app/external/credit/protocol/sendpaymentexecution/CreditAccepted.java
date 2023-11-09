package com.pubsubdoc.payment.service.web.app.external.credit.protocol.sendpaymentexecution;

import com.pubsubdoc.payment.service.shared.application.doc.models.payment.PaymentId;

public record CreditAccepted(PaymentId paymentId) {
}
