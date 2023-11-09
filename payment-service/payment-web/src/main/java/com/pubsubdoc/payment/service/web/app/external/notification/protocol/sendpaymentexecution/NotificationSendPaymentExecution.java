package com.pubsubdoc.payment.service.web.app.external.notification.protocol.sendpaymentexecution;

import com.pubsubdoc.payment.service.shared.application.doc.models.payment.PaymentId;

public record NotificationSendPaymentExecution(PaymentId paymentId) {
}
