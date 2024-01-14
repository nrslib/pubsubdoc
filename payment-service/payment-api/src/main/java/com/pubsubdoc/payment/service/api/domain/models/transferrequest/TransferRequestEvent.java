package com.pubsubdoc.payment.service.api.domain.models.transferrequest;

import com.pubsubdoc.payment.service.shared.application.doc.models.transferrequest.TransferRequestId;

public interface TransferRequestEvent {
    TransferRequestId transferRequestId();
}
