package com.pubsubdoc.payment.service.web.app.application.external.notification;

import com.pubsubdoc.payment.service.web.app.adaptor.aggregates.paymentprocess.PaymentProcessAggregate;
import com.pubsubdoc.payment.service.web.app.application.external.notification.protocol.sendpaymentexecution.NotificationPaymentExecutionSent;
import com.pubsubdoc.payment.service.web.app.application.external.notification.protocol.sendpaymentexecution.NotificationSendPaymentExecution;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.stereotype.Service;

@Service
public record NotificationService(EventGateway eventGateway, EventSourcingRepository<PaymentProcessAggregate> paymentAggregateRepository) {
    //public record NotificationService(EventGateway eventGateway) {
    @CommandHandler
    public void handle(NotificationSendPaymentExecution command) {
        paymentAggregateRepository.load(command.paymentProcessId().asString())
                .execute(paymentProcessAggregate -> {
                    var payment = paymentProcessAggregate.getAggregate();

                    // Call http client to send notification.

                    eventGateway.publish(new NotificationPaymentExecutionSent(command.paymentProcessId(), payment.userId()));
                });
    }
}
