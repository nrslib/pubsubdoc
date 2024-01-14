package com.pubsubdoc.payment.service.web.app.adaptor.aggregates.transferrequest.commands;

import com.pubsubdoc.payment.service.shared.application.doc.models.paymentprocess.PaymentProcessId;
import com.pubsubdoc.user.service.shared.application.doc.models.user.UserId;

import java.math.BigDecimal;

public record TransferRequestCreate(PaymentProcessId paymentProcessId, UserId userId, BigDecimal amount) {
}
