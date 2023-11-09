package com.pubsubdoc.doc.service.web.app.adaptor.aggregates.doc.commands;

import com.pubsubdoc.doc.service.shared.application.doc.models.doc.DocId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public interface DocCommand {
    @TargetAggregateIdentifier
    DocId docId();
}
