package com.pubsubdoc.user.service.web.app.application.domain.models.user;

import com.example.appkit.application.basic.EventDrivenAggregateRoot;
import com.pubsubdoc.user.service.api.domain.models.user.UserCreated;
import com.pubsubdoc.user.service.api.domain.models.user.UserEvent;
import com.pubsubdoc.user.service.shared.application.doc.models.user.UserId;

public record User(UserId userId) implements EventDrivenAggregateRoot<UserEvent> {
    public static UserCreated create(UserId docId, String name) {
        return new UserCreated(docId, name);
    }

    public static User applyEvent(UserCreated event) {
        return new User(event.userId());
    }

    @Override
    public EventDrivenAggregateRoot<UserEvent> applyEvent(UserEvent event) {
        return switch (event) {
            case UserCreated __ -> new User(userId);
            default -> throw new IllegalStateException("Unexpected value: " + event);
        };
    }
}
