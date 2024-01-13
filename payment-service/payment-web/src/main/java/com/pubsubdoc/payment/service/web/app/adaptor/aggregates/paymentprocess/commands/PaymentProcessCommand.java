package com.pubsubdoc.payment.service.web.app.adaptor.aggregates.paymentprocess.commands;

import com.pubsubdoc.payment.service.shared.application.doc.models.paymentprocess.PaymentProcessId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public interface PaymentProcessCommand {
    @TargetAggregateIdentifier
    PaymentProcessId paymentProcessId();
}
