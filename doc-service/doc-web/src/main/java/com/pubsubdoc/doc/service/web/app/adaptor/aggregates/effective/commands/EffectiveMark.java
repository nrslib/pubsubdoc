package com.pubsubdoc.doc.service.web.app.adaptor.aggregates.effective.commands;

import com.pubsubdoc.doc.service.shared.application.doc.models.doc.DocId;

import java.util.UUID;

public record EffectiveMark(DocId docId, UUID userId) implements EffectiveCommand {
    @Override
    public String getIdentifier() {
        return docId.asString() + ":" + userId;
    }
}
