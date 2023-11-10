package com.pubsubdoc.doc.service.web.app.adaptor.aggregates.doc;

import com.example.axon.application.adaptor.AbstractAggregate;
import com.pubsubdoc.doc.service.api.domain.models.doc.DocCreated;
import com.pubsubdoc.doc.service.api.domain.models.doc.DocEvent;
import com.pubsubdoc.doc.service.shared.application.doc.models.doc.DocId;
import com.pubsubdoc.doc.service.web.app.adaptor.aggregates.doc.commands.create.DocCreate;
import com.pubsubdoc.doc.service.web.app.adaptor.aggregates.doc.commands.create.DocUpdate;
import com.pubsubdoc.doc.service.web.app.application.domain.models.doc.Doc;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class DocAggregate extends AbstractAggregate<Doc, DocId, DocEvent> {
    @Override
    protected DocId getAggregateRootId(Doc aggregate) {
        if (aggregate != null) {
            return aggregate.docId();
        } else {
            return null;
        }
    }

    @Override
    protected Doc newAggregateRootByEvent(DocEvent event) {
        return Doc.applyEvent((DocCreated) event);
    }

    @Override
    protected boolean isConstructEvent(DocEvent event) {
        return event instanceof DocCreated;
    }

    public DocAggregate() {
    }

    @CommandHandler
    public DocAggregate(DocCreate command) {
        var event = Doc.create(command.docId(), command.body());
        apply(event);
    }

    @CommandHandler
    public void handle(DocUpdate command) {
        apply(it -> it.update(command.body()));
    }
}
