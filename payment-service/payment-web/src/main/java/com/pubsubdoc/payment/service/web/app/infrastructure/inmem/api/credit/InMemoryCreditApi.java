package com.pubsubdoc.payment.service.web.app.infrastructure.inmem.api.credit;

import com.pubsubdoc.payment.service.web.app.infrastructure.api.credit.CreditApi;
import com.pubsubdoc.payment.service.web.app.infrastructure.api.credit.makepayment.CreditMakePaymentRequest;
import com.pubsubdoc.payment.service.web.app.infrastructure.api.credit.makepayment.CreditMakePaymentResponse;

public class InMemoryCreditApi implements CreditApi {
    @Override
    public CreditMakePaymentResponse makePayment(CreditMakePaymentRequest request) {
        return new CreditMakePaymentResponse(true, 200);
    }
}
