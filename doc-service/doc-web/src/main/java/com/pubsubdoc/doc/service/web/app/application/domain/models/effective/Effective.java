package com.pubsubdoc.doc.service.web.app.application.domain.models.effective;

import com.example.appkit.application.basic.EventDrivenAggregateRoot;
import com.pubsubdoc.doc.service.api.domain.models.effective.EffectiveEvent;
import com.pubsubdoc.doc.service.api.domain.models.effective.EffectiveMarked;
import com.pubsubdoc.doc.service.api.domain.models.effective.EffectiveUnmarked;
import com.pubsubdoc.doc.service.shared.application.doc.models.doc.DocId;
import com.pubsubdoc.doc.service.shared.application.doc.models.effective.EffectiveId;
import com.pubsubdoc.doc.service.web.app.application.domain.models.effective.exceptions.EffectiveMarkedException;
import com.pubsubdoc.doc.service.web.app.application.domain.models.effective.exceptions.EffectiveUnmarkedException;
import fj.data.Either;

import java.util.UUID;

public record Effective(EffectiveId effectiveId, boolean marked) implements EventDrivenAggregateRoot<EffectiveEvent> {
    public static EffectiveMarked create(DocId docId, UUID userId) {
        return new EffectiveMarked(docId, userId);
    }

    public static Effective applyEvent(EffectiveMarked event) {
        return new Effective(new EffectiveId(event.docId(), event.userId()), true);
    }

    public Either<EffectiveMarkedException, EffectiveMarked> mark() {
        if (marked) {
            return Either.left(new EffectiveMarkedException());
        }

        return Either.right(new EffectiveMarked(effectiveId.docId(), effectiveId.userId()));
    }

    public Either<EffectiveUnmarkedException, EffectiveUnmarked> unmark() {
        if (!marked) {
            return Either.left(new EffectiveUnmarkedException());
        }

        return Either.right(new EffectiveUnmarked(effectiveId.docId(), effectiveId.userId()));
    }

    @Override
    public EventDrivenAggregateRoot<EffectiveEvent> applyEvent(EffectiveEvent event) {
        return switch (event) {
            case EffectiveMarked __ -> new Effective(effectiveId, true);
            case EffectiveUnmarked __ -> new Effective(effectiveId, false);
            default -> throw new IllegalStateException("Unexpected value: " + event);
        };
    }
}
