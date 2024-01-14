package com.pubsubdoc.payment.service.web.app.application.query.transferrequest;

import com.pubsubdoc.payment.service.api.domain.models.transferrequest.TransferRequestStarted;
import com.pubsubdoc.payment.service.web.app.application.query.transferrequest.datamodel.TransferRequestDataModel;
import com.pubsubdoc.payment.service.web.app.application.query.transferrequest.datamodel.TransferRequestStatus;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.ResetHandler;
import org.springframework.stereotype.Component;

@Component
public record TransferRequestProjection(TransferRequestRepository repository) {
    @ResetHandler
    public void reset() {
        repository.deleteAll();
    }

    @EventHandler
    public void on(TransferRequestStarted event) {
        var data = new TransferRequestDataModel();
        data.setTransferRequestId(event.transferRequestId().asString());
        data.setStatus(TransferRequestStatus.CREATED);

        repository.save(data);
    }
}
