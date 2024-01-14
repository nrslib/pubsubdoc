package com.pubsubdoc.payment.service.api.domain.models.transferrequest;

import com.pubsubdoc.payment.service.shared.application.doc.models.transferrequest.TransferRequestId;

public record TransferRequestStarted(TransferRequestId transferRequestId) implements TransferRequestEvent {
}
