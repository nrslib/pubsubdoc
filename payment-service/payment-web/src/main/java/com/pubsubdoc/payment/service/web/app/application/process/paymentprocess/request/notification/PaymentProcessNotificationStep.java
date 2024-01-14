package com.pubsubdoc.payment.service.web.app.application.process.paymentprocess.request.notification;

import com.pubsubdoc.payment.service.web.app.application.external.credit.protocol.sendpaymentexecution.CreditAccepted;
import com.pubsubdoc.payment.service.web.app.application.external.notification.protocol.sendpaymentexecution.NotificationSendPaymentExecution;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public record PaymentProcessNotificationStep(CommandGateway commandHandler) {
    @EventHandler
    public void on(CreditAccepted event) {
        commandHandler.send(new NotificationSendPaymentExecution(event.paymentProcessId()));
    }
}
