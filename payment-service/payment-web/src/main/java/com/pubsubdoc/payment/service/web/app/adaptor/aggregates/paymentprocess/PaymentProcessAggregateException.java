package com.pubsubdoc.payment.service.web.app.adaptor.aggregates.paymentprocess;

import com.pubsubdoc.payment.service.web.app.application.domain.models.paymentprocess.errors.PaymentProcessError;

public class PaymentProcessAggregateException extends RuntimeException {
    public PaymentProcessAggregateException(PaymentProcessError error) {
        super(error.toString());
    }
}
