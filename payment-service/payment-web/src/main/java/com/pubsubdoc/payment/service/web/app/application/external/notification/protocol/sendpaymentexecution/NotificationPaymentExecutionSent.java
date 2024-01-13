package com.pubsubdoc.payment.service.web.app.application.external.notification.protocol.sendpaymentexecution;

import com.pubsubdoc.payment.service.shared.application.doc.models.paymentprocess.PaymentProcessId;
import com.pubsubdoc.user.service.shared.application.doc.models.user.UserId;

public record NotificationPaymentExecutionSent(PaymentProcessId paymentProcessId, UserId userId) {
}
