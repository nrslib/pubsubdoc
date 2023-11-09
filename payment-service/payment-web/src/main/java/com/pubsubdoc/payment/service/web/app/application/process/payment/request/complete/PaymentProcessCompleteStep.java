package com.pubsubdoc.payment.service.web.app.application.process.payment.request.complete;

import com.pubsubdoc.payment.service.web.app.adaptor.aggregates.payment.commands.PaymentComplete;
import com.pubsubdoc.payment.service.web.app.external.notification.protocol.sendpaymentexecution.NotificationPaymentExecutionSent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public record PaymentProcessCompleteStep(CommandGateway commandGateway) {
    @EventHandler
    public void on(NotificationPaymentExecutionSent event) {
        commandGateway.send(new PaymentComplete(event.paymentId()));
    }
}
