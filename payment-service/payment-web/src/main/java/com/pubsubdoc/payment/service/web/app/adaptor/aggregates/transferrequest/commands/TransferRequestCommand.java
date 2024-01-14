package com.pubsubdoc.payment.service.web.app.adaptor.aggregates.transferrequest.commands;

import com.pubsubdoc.payment.service.shared.application.doc.models.transferrequest.TransferRequestId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public interface TransferRequestCommand {
    @TargetAggregateIdentifier
    TransferRequestId transferRequestId();
}
