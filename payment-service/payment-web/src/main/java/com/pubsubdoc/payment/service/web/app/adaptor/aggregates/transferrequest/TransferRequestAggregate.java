package com.pubsubdoc.payment.service.web.app.adaptor.aggregates.transferrequest;

import com.example.axon.application.adaptor.AbstractAggregate;
import com.pubsubdoc.payment.service.api.domain.models.transferrequest.TransferRequestEvent;
import com.pubsubdoc.payment.service.api.domain.models.transferrequest.TransferRequestStarted;
import com.pubsubdoc.payment.service.shared.application.doc.models.transferrequest.TransferRequestId;
import com.pubsubdoc.payment.service.web.app.adaptor.aggregates.transferrequest.commands.TransferRequestCreate;
import com.pubsubdoc.payment.service.web.app.application.domain.models.transferrequest.TransferRequest;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class TransferRequestAggregate extends AbstractAggregate<TransferRequest, TransferRequestId, TransferRequestEvent> {
    @Override
    protected TransferRequestId getAggregateRootId(TransferRequest aggregate) {
        if (aggregate != null) {
            return aggregate.transferRequestId();
        } else {
            return null;
        }
    }
    @Override
    protected TransferRequest newAggregateRootByEvent(TransferRequestEvent transferRequestEvent) {
        return TransferRequest.applyEvent((TransferRequestStarted) transferRequestEvent);
    }
    @Override
    protected boolean isConstructEvent(TransferRequestEvent transferRequestEvent) {
        return transferRequestEvent instanceof TransferRequestStarted;
    }

    protected TransferRequestAggregate() {
    }

    @CommandHandler
    public TransferRequestAggregate(TransferRequestCreate command) {
        var event = TransferRequest.create(new TransferRequestId());
        apply(event);
    }
}
