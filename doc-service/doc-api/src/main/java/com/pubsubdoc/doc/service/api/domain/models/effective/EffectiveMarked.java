package com.pubsubdoc.doc.service.api.domain.models.effective;

import com.pubsubdoc.doc.service.shared.application.doc.models.doc.DocId;

import java.util.UUID;

public record EffectiveMarked(DocId docId, UUID userId) implements EffectiveEvent {
}
