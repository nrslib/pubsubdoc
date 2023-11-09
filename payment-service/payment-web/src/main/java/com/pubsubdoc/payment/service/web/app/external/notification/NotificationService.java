package com.pubsubdoc.payment.service.web.app.external.notification;

import com.pubsubdoc.payment.service.web.app.adaptor.aggregates.payment.PaymentAggregate;
import com.pubsubdoc.payment.service.web.app.external.notification.protocol.sendpaymentexecution.NotificationPaymentExecutionSent;
import com.pubsubdoc.payment.service.web.app.external.notification.protocol.sendpaymentexecution.NotificationSendPaymentExecution;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.stereotype.Service;

@Service
public record NotificationService(EventGateway eventGateway, EventSourcingRepository<PaymentAggregate> paymentAggregateRepository) {
    //public record NotificationService(EventGateway eventGateway) {
    @CommandHandler
    public void handle(NotificationSendPaymentExecution command) {
        paymentAggregateRepository.load(command.paymentId().asString())
                .execute(paymentAggregate -> {
                    var payment = paymentAggregate.getAggregate();

                    // Call http client to send notification.

                    eventGateway.publish(new NotificationPaymentExecutionSent(command.paymentId(), payment.userId()));
                });
    }
}
