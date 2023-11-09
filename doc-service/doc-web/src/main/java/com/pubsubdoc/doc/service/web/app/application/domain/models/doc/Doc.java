package com.pubsubdoc.doc.service.web.app.application.domain.models.doc;

import com.example.appkit.application.basic.EventDrivenAggregateRoot;
import com.pubsubdoc.doc.service.api.domain.models.doc.DocCreated;
import com.pubsubdoc.doc.service.api.domain.models.doc.DocEvent;
import com.pubsubdoc.doc.service.api.domain.models.doc.DocUpdated;
import com.pubsubdoc.doc.service.shared.application.doc.models.doc.DocId;

public record Doc(
        DocId docId,
        String body
) implements EventDrivenAggregateRoot<DocEvent> {
    public static DocCreated create(DocId docId, String body) {
        return new DocCreated(docId, body);
    }

    public static Doc applyEvent(DocCreated event) {
        return new Doc(event.docId(), event.body());
    }

    public DocUpdated update(String body) {
        return new DocUpdated(docId, body);
    }

    @Override
    public EventDrivenAggregateRoot<DocEvent> applyEvent(DocEvent event) {
        return switch (event) {
            case DocCreated docCreated -> new Doc(docCreated.docId(), docCreated.body());
            case DocUpdated docUpdated -> new Doc(this.docId, docUpdated.body());
            default -> throw new IllegalStateException("Unexpected value: " + event);
        };
    }
}
