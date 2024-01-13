package com.pubsubdoc.payment.service.web.app.application.external.notification.protocol.sendpaymentexecution;

import com.pubsubdoc.payment.service.shared.application.doc.models.paymentprocess.PaymentProcessId;

public record NotificationSendPaymentExecution(PaymentProcessId paymentProcessId) {
}
