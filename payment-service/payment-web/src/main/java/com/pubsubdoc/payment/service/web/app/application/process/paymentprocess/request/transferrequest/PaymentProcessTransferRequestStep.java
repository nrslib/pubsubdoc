package com.pubsubdoc.payment.service.web.app.application.process.paymentprocess.request.transferrequest;

import com.pubsubdoc.payment.service.api.domain.models.paymentprocess.PaymentProcessRequested;
import com.pubsubdoc.payment.service.shared.application.doc.models.paymentprocess.PaymentMethod;
import com.pubsubdoc.payment.service.web.app.adaptor.aggregates.transferrequest.commands.TransferRequestCreate;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public record PaymentProcessTransferRequestStep(CommandGateway commandGateway) {
    @EventHandler
    public void on(PaymentProcessRequested event) {
        if (event.paymentMethod() == PaymentMethod.BANK_TRANSFER) {
            var command = new TransferRequestCreate(event.paymentProcessId(), event.userId(), event.amount());
            commandGateway.send(command);
        }
    }
}
