package com.pubsubdoc.user.service.web.listener.paymentservice;

import com.pubsubdoc.payment.service.api.domain.models.paymentprocess.PaymentProcessCompleted;
import com.pubsubdoc.user.service.web.app.adaptor.aggregates.user.commands.UserUpgrade;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public record PaymentServiceListener(CommandGateway commandGateway) {
    @EventHandler
    public void on(PaymentProcessCompleted event) {
        commandGateway.send(new UserUpgrade(event.userId()));
    }
}
