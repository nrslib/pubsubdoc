package com.pubsubdoc.doc.service.shared.application.doc.models.doc;

import com.example.appkit.application.basic.IdObject;

import java.util.UUID;

public record DocId(UUID value) implements IdObject {
    public DocId() {
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
