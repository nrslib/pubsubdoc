package com.pubsubdoc.payment.service.web.app.infrastructure.api.credit;

import com.pubsubdoc.payment.service.web.app.infrastructure.api.credit.makepayment.CreditMakePaymentRequest;
import com.pubsubdoc.payment.service.web.app.infrastructure.api.credit.makepayment.CreditMakePaymentResponse;

public interface CreditApi {
    public CreditMakePaymentResponse makePayment(CreditMakePaymentRequest request);
}
