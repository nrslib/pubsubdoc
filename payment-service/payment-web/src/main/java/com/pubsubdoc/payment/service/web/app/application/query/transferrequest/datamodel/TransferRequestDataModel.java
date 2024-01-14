package com.pubsubdoc.payment.service.web.app.application.query.transferrequest.datamodel;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "transfer_requests")
public class TransferRequestDataModel {
    @Id
    String transferRequestId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    TransferRequestStatus status;
}
