package com.pubsubdoc.payment.service.web.app.application.external.credit.protocol.sendpaymentexecution;

public record CreditMakePaymentFailed(CreditApply command, String message) {
}
