package com.pubsubdoc.payment.service.web.app.external.notification.protocol.sendpaymentexecution;

import com.pubsubdoc.payment.service.shared.application.doc.models.payment.PaymentId;
import com.pubsubdoc.user.service.shared.application.doc.models.user.UserId;

public record NotificationPaymentExecutionSent(PaymentId paymentId, UserId userId) {
}
