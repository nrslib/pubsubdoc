package com.pubsubdoc.doc.service.web.http.models.effectives.post;

import java.util.UUID;

public record EffectivesPostRequest(
        UUID docId,
        UUID userId
) {}
