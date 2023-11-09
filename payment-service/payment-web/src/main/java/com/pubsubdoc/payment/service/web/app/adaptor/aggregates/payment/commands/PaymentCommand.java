package com.pubsubdoc.payment.service.web.app.adaptor.aggregates.payment.commands;

import com.pubsubdoc.payment.service.shared.application.doc.models.payment.PaymentId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public interface PaymentCommand {
    @TargetAggregateIdentifier
    PaymentId paymentId();
}
