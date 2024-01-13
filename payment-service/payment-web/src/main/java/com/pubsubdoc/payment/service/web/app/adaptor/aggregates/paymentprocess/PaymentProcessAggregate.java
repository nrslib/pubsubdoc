package com.pubsubdoc.payment.service.web.app.adaptor.aggregates.paymentprocess;

import com.example.axon.application.adaptor.AbstractAggregate;
import com.pubsubdoc.payment.service.api.domain.models.paymentprocess.PaymentProcessEvent;
import com.pubsubdoc.payment.service.api.domain.models.paymentprocess.PaymentProcessRequested;
import com.pubsubdoc.payment.service.shared.application.doc.models.paymentprocess.PaymentProcessId;
import com.pubsubdoc.payment.service.web.app.adaptor.aggregates.paymentprocess.commands.PaymentProcessComplete;
import com.pubsubdoc.payment.service.web.app.adaptor.aggregates.paymentprocess.commands.PaymentProcessFail;
import com.pubsubdoc.payment.service.web.app.adaptor.aggregates.paymentprocess.commands.PaymentRequest;
import com.pubsubdoc.payment.service.web.app.application.domain.models.paymentprocess.PaymentProcess;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.messaging.MetaData;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class PaymentProcessAggregate extends AbstractAggregate<PaymentProcess, PaymentProcessId, PaymentProcessEvent> {

    @Override
    protected PaymentProcessId getAggregateRootId(PaymentProcess aggregate) {
        if (aggregate != null) {
            return aggregate.paymentProcessId();
        } else {
            return null;
        }
    }

    @Override
    protected PaymentProcess newAggregateRootByEvent(PaymentProcessEvent event) {
        return PaymentProcess.applyEvent((PaymentProcessRequested) event);
    }

    @Override
    protected boolean isConstructEvent(PaymentProcessEvent event) {
        return event instanceof PaymentProcessRequested;
    }

    protected PaymentProcessAggregate() {
    }

    @CommandHandler
    public PaymentProcessAggregate(PaymentRequest command) {
        var event = PaymentProcess.create(new PaymentProcessId(), command.userId(), command.amount(), command.paymentMethod());
        apply(event, MetaData.with("processId", event.paymentProcessId().value()));
    }

    @CommandHandler
    public void handle(PaymentProcessComplete command) {
        apply(PaymentProcess::complete);
    }

    @CommandHandler
    public void handle(PaymentProcessFail command) {
        apply(PaymentProcess::fail);
    }
}