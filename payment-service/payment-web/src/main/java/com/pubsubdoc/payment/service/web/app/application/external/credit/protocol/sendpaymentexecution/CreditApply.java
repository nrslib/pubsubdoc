package com.pubsubdoc.payment.service.web.app.application.external.credit.protocol.sendpaymentexecution;

import com.example.axon.application.external.retry.RetryableCommand;
import com.pubsubdoc.payment.service.shared.application.doc.models.paymentprocess.PaymentProcessId;
import com.pubsubdoc.user.service.shared.application.doc.models.user.UserId;

import java.math.BigDecimal;

public record CreditApply(PaymentProcessId paymentProcessId, UserId userId, BigDecimal amount, int count) implements RetryableCommand {
    public CreditApply(PaymentProcessId paymentProcessId, UserId userId, BigDecimal amount) {
        this(paymentProcessId, userId, amount, 0);
    }

    public CreditApply countUp() {
        return new CreditApply(paymentProcessId, userId, amount, count + 1);
    }
}
