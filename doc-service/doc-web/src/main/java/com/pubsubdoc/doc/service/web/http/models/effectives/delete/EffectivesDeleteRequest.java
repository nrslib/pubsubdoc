package com.pubsubdoc.doc.service.web.http.models.effectives.delete;

import java.util.UUID;

public record EffectivesDeleteRequest(
        UUID docId,
        UUID userId
) {
}
