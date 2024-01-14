package com.pubsubdoc.payment.service.web.app.application.query.transferrequest;

import com.pubsubdoc.payment.service.web.app.application.query.transferrequest.datamodel.TransferRequestDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRequestRepository extends JpaRepository<TransferRequestDataModel, String> {
}
