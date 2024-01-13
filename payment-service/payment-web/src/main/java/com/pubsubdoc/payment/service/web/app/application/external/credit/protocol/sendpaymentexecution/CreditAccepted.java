package com.pubsubdoc.payment.service.web.app.application.external.credit.protocol.sendpaymentexecution;

import com.pubsubdoc.payment.service.shared.application.doc.models.paymentprocess.PaymentProcessId;

public record CreditAccepted(PaymentProcessId paymentProcessId) {
}
