package com.pubsubdoc.payment.service.web.app.adaptor.aggregates.payment;

import com.example.axon.application.adaptor.AbstractAggregate;
import com.pubsubdoc.payment.service.api.domain.models.payment.PaymentEvent;
import com.pubsubdoc.payment.service.api.domain.models.payment.PaymentFailed;
import com.pubsubdoc.payment.service.api.domain.models.payment.PaymentRequested;
import com.pubsubdoc.payment.service.shared.application.doc.models.payment.PaymentId;
import com.pubsubdoc.payment.service.web.app.adaptor.aggregates.payment.commands.PaymentComplete;
import com.pubsubdoc.payment.service.web.app.adaptor.aggregates.payment.commands.PaymentFail;
import com.pubsubdoc.payment.service.web.app.adaptor.aggregates.payment.commands.PaymentRequest;
import com.pubsubdoc.payment.service.web.app.application.domain.models.payment.Payment;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class PaymentAggregate extends AbstractAggregate<Payment, PaymentId, PaymentEvent> {

    @Override
    protected PaymentId getAggregateRootId(Payment aggregate) {
        if (aggregate != null) {
            return aggregate.paymentId();
        } else {
            return null;
        }
    }
    @Override
    protected Payment newAggregateRootByEvent(PaymentEvent event) {
        return Payment.applyEvent((PaymentRequested) event);
    }
    @Override
    protected boolean isConstructEvent(PaymentEvent event) {
        return event instanceof PaymentRequested;
    }

    protected PaymentAggregate() {}

    @CommandHandler
    public PaymentAggregate(PaymentRequest command) {
        var event = Payment.create(new PaymentId(), command.userId(), command.amount());
        apply(event);
    }

    @CommandHandler
    public void handle(PaymentComplete command) {
        apply(Payment::complete);
    }

    @CommandHandler
    public void handle(PaymentFail command) {
        apply(Payment::fail);
    }
}