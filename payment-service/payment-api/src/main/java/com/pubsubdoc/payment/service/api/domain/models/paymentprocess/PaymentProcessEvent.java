package com.pubsubdoc.payment.service.api.domain.models.paymentprocess;

import com.pubsubdoc.payment.service.shared.application.doc.models.paymentprocess.PaymentProcessId;

public interface PaymentProcessEvent {
    PaymentProcessId paymentProcessId();
}
