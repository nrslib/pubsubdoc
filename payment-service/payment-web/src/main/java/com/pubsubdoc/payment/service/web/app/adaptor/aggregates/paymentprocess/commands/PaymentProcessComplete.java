package com.pubsubdoc.payment.service.web.app.adaptor.aggregates.paymentprocess.commands;

import com.pubsubdoc.payment.service.shared.application.doc.models.paymentprocess.PaymentProcessId;

public record PaymentProcessComplete(PaymentProcessId paymentProcessId) implements PaymentProcessCommand {
}
