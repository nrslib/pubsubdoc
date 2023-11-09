package com.pubsubdoc.user.service.shared.application.doc.models.team;

import com.example.appkit.application.basic.IdObject;

import java.util.UUID;

public record TeamId (UUID value) implements IdObject {
    public TeamId() {
        this(UUID.randomUUID());
    }

    @Override
    public String asString() {
        return value.toString();
    }

    @Override
    public String toString() {
        return asString();
    }
}
