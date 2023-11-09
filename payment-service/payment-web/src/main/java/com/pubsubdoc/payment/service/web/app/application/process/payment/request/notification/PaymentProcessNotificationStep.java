package com.pubsubdoc.payment.service.web.app.application.process.payment.request.notification;

import com.pubsubdoc.payment.service.web.app.external.credit.protocol.sendpaymentexecution.CreditAccepted;
import com.pubsubdoc.payment.service.web.app.external.notification.protocol.sendpaymentexecution.NotificationSendPaymentExecution;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public record PaymentProcessNotificationStep(CommandGateway commandHandler) {
    @EventHandler
    public void on(CreditAccepted event) {
        commandHandler.send(new NotificationSendPaymentExecution(event.paymentId()));
    }
}
