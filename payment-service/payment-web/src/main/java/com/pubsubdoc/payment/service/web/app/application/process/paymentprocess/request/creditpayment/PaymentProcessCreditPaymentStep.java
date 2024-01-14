package com.pubsubdoc.payment.service.web.app.application.process.paymentprocess.request.creditpayment;

import com.pubsubdoc.payment.service.api.domain.models.paymentprocess.PaymentProcessRequested;
import com.pubsubdoc.payment.service.shared.application.doc.models.paymentprocess.PaymentMethod;
import com.pubsubdoc.payment.service.web.app.application.external.credit.protocol.sendpaymentexecution.CreditApply;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public record PaymentProcessCreditPaymentStep(CommandGateway commandGateway) {
    @EventHandler
    public void on(PaymentProcessRequested event) {
        if (event.paymentMethod() == PaymentMethod.CREDIT) {
            commandGateway.send(new CreditApply(event.paymentProcessId(), event.userId(), event.amount()));
        }
    }
}
