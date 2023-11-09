package com.pubsubdoc.doc.service.web.app.adaptor.aggregates.effective;

import com.example.axon.application.adaptor.AbstractAggregate;
import com.pubsubdoc.doc.service.api.domain.models.effective.EffectiveEvent;
import com.pubsubdoc.doc.service.api.domain.models.effective.EffectiveMarked;
import com.pubsubdoc.doc.service.shared.application.doc.models.effective.EffectiveId;
import com.pubsubdoc.doc.service.web.app.adaptor.aggregates.effective.commands.EffectiveMark;
import com.pubsubdoc.doc.service.web.app.adaptor.aggregates.effective.commands.EffectiveUnmark;
import com.pubsubdoc.doc.service.web.app.application.domain.models.effective.Effective;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateCreationPolicy;
import org.axonframework.modelling.command.CreationPolicy;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class EffectiveAggregate extends AbstractAggregate<Effective, EffectiveId, EffectiveEvent> {
    @Override
    protected EffectiveId getAggregateRootId(Effective aggregate) {
        if (aggregate != null) {
            return aggregate.effectiveId();
        } else {
            return null;
        }
    }
    @Override
    protected Effective newAggregateRootByEvent(EffectiveEvent event) {
        if (event instanceof EffectiveMarked) {
            return Effective.applyEvent((EffectiveMarked) event);
        }

        throw new IllegalArgumentException();
    }
    @Override
    protected boolean isConstructEvent(EffectiveEvent event) {
        return event instanceof EffectiveMarked;
    }

    @CommandHandler
    @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
    public void handle(EffectiveMark command) {
        var aggregate = getAggregate();
        if (aggregate == null) {
            var event = Effective.create(command.docId(), command.userId());
            apply(event);
        } else {
            var result = aggregate.mark();
            result.right().forEach(this::apply);
        }
    }

    @CommandHandler
    public void handle(EffectiveUnmark command) {
        var result = getAggregate().unmark();
        result.right().forEach(this::apply);
    }
}
