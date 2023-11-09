package com.pubsubdoc.doc.service.shared.application.doc.models.effective;

import com.example.appkit.application.basic.IdObject;
import com.pubsubdoc.doc.service.shared.application.doc.models.doc.DocId;

import java.util.UUID;

public record EffectiveId(DocId docId, UUID userId) implements IdObject {
    public EffectiveId() {
        this(new DocId(), UUID.randomUUID());
    }

    public String getIdentifier() {
        return docId.asString() + ":" + userId.toString();
    }

    @Override
    public String asString() {
        return getIdentifier();
    }

    @Override
    public String toString() {
        return asString();
    }
}
