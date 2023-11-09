package com.pubsubdoc.payment.service.web.app.adaptor.aggregates.payment.commands;

import com.pubsubdoc.payment.service.shared.application.doc.models.payment.PaymentId;

public record PaymentComplete(PaymentId paymentId) implements PaymentCommand {
}
