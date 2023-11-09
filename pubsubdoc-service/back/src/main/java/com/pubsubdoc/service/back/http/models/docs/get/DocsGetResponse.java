package com.pubsubdoc.service.back.http.models.docs.get;

import java.util.UUID;

public record DocsGetResponse(UUID docsId, String body, int effectiveCount) {
}
