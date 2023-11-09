package com.pubsubdoc.payment.service.web.app.external.credit;

import com.pubsubdoc.payment.service.web.app.application.query.user.queries.GetUser;
import com.pubsubdoc.payment.service.web.app.application.query.user.queries.GetUserResult;
import com.pubsubdoc.payment.service.web.app.external.credit.protocol.sendpaymentexecution.CreditAccepted;
import com.pubsubdoc.payment.service.web.app.external.credit.protocol.sendpaymentexecution.CreditApply;
import com.pubsubdoc.payment.service.web.app.external.credit.protocol.sendpaymentexecution.CreditRejected;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public record CreditService(QueryGateway queryGateway, EventGateway eventGateway) {
    @CommandHandler
    public void handle(CreditApply command) throws ExecutionException, InterruptedException {
        var criteria = new GetUser(command.userId().value());
        var result = queryGateway.query(criteria, GetUserResult.class).get();

        // Call http client.

        result.userDataModel().ifPresentOrElse(
                user -> eventGateway.publish(new CreditAccepted(command.paymentId())),
                () -> eventGateway.publish(new CreditRejected(command.paymentId()))
        );
    }
}
