package com.pubsubdoc.payment.service.web.app.application.external.credit;

import com.example.axon.application.external.retry.ExternalRetryRequested;
import com.example.axon.application.external.retry.RetryScheduler;
import com.pubsubdoc.payment.service.web.app.application.external.credit.protocol.sendpaymentexecution.*;
import com.pubsubdoc.payment.service.web.app.infrastructure.api.credit.CreditApi;
import com.pubsubdoc.payment.service.web.app.infrastructure.api.credit.makepayment.CreditMakePaymentRequest;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.axonframework.eventhandling.scheduling.EventScheduler;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

@Service
public record CreditService(CreditApi creditApi, QueryGateway queryGateway, EventGateway eventGateway, EventScheduler scheduler) {
    @CommandHandler
    public void handle(CreditApply command) {
        var response = creditApi.makePayment(new CreditMakePaymentRequest(command.userId(), command.amount()));
        if (response.statusCode() != 200) {
            eventGateway.publish(new CreditMakePaymentFailed(command, null));
            return;
        }

        if (response.accepted()) {
            eventGateway.publish(new CreditAccepted(command.paymentProcessId()));
        } else {
            eventGateway.publish(new CreditRejected(command.paymentProcessId()));
        }
    }

    @EventHandler
    public void on(CreditMakePaymentFailed event) {
        RetryScheduler.exponentialBackoff(scheduler, event.command().count(), 5, 60, (duration) -> {
            eventGateway.publish(new CreditMakePaymentCriticalErrorOccurred());
        }, () -> {
            var command = event.command().countUp();
            return new ExternalRetryRequested(command);
        });
    }
}
