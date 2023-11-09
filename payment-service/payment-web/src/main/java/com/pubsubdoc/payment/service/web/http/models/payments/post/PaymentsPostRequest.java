package com.pubsubdoc.payment.service.web.http.models.payments.post;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentsPostRequest(UUID userId, BigDecimal amount) {
}
