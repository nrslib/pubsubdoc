package com.pubsubdoc.payment.service.web.app.application.process.payment.request.execution;

import com.pubsubdoc.payment.service.api.domain.models.payment.PaymentRequested;
import com.pubsubdoc.payment.service.web.app.external.credit.protocol.sendpaymentexecution.CreditApply;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.MetaData;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public record PaymentProcessExecutionStep(CommandGateway commandGateway) {
    @EventHandler
    public void on(PaymentRequested event) {
        commandGateway.send(new CreditApply(event.paymentId(), event.userId(), event.amount()), new MetaData(Map.of("processId", event.paymentId().value())));
    }
}
