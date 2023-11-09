package com.pubsubdoc.user.service.api.domain.models.user;

import com.pubsubdoc.user.service.shared.application.doc.models.user.UserId;

public record UserCreated(
        UserId userId,
        String name
) implements UserEvent {
}
