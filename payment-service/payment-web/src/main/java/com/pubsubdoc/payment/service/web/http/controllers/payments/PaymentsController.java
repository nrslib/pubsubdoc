package com.pubsubdoc.payment.service.web.http.controllers.payments;

import com.pubsubdoc.payment.service.shared.application.doc.models.paymentprocess.PaymentProcessId;
import com.pubsubdoc.payment.service.web.app.adaptor.aggregates.paymentprocess.commands.PaymentRequest;
import com.pubsubdoc.payment.service.web.http.models.payments.post.PaymentsPostRequest;
import com.pubsubdoc.payment.service.web.http.models.payments.post.PaymentsPostResponse;
import com.pubsubdoc.user.service.shared.application.doc.models.user.UserId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(description = "Payment API", name = "Payment API")
@RestController
@RequestMapping("/api/payments")
public record PaymentsController(CommandGateway commandGateway) {
    @Operation(summary = "Request payment.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentsPostResponse post(PaymentsPostRequest request) {
        var userId = new UserId(request.userId());
        PaymentProcessId paymentProcessId = commandGateway.sendAndWait(new PaymentRequest(userId, request.amount(), request.paymentMethod()));

        return new PaymentsPostResponse(paymentProcessId.value());
    }
}
