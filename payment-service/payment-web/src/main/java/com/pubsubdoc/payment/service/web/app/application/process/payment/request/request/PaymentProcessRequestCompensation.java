package com.pubsubdoc.payment.service.web.app.application.process.payment.request.request;

import com.pubsubdoc.payment.service.web.app.adaptor.aggregates.payment.commands.PaymentFail;
import com.pubsubdoc.payment.service.web.app.external.credit.protocol.sendpaymentexecution.CreditRejected;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public record PaymentProcessRequestCompensation(CommandGateway commandGateway) {
    @EventHandler
    public void on(CreditRejected event) {
        commandGateway.send(new PaymentFail(event.paymentId()));
    }
}
