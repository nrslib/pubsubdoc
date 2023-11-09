package com.pubsubdoc.service.back.app.application.query.doc;

import com.pubsubdoc.doc.service.api.domain.models.doc.DocCreated;
import com.pubsubdoc.doc.service.api.domain.models.doc.DocUpdated;
import com.pubsubdoc.doc.service.api.domain.models.effective.EffectiveMarked;
import com.pubsubdoc.doc.service.api.domain.models.effective.EffectiveUnmarked;
import com.pubsubdoc.service.back.app.infrastructure.jpa.doc.DocDataModel;
import com.pubsubdoc.service.back.app.infrastructure.jpa.doc.DocRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.ResetHandler;
import org.springframework.stereotype.Component;

@Component
public class DocProjection {
    private final DocRepository repository;

    public DocProjection(DocRepository repository) {this.repository = repository;}

    @ResetHandler
    public void reset() {
        this.repository.deleteAll();
    }

    @EventHandler
    public void on(DocCreated event) {
        var doc = new DocDataModel();
        doc.setDocId(event.docId().asString());
        doc.setBody(event.body());
        repository.save(doc);
    }

    @EventHandler
    public void on(DocUpdated event) {
        var doc = repository.findById(event.docId().asString()).orElseThrow();
        doc.setBody(event.body());
        repository.save(doc);
    }

    @EventHandler
    public void on(EffectiveMarked event) {
        var doc = repository.findById(event.docId().asString()).orElseThrow();
        doc.setEffectiveCount(doc.getEffectiveCount() + 1);
        repository.save(doc);
    }

    @EventHandler
    public void on(EffectiveUnmarked event) {
        var doc = repository.findById(event.docId().asString()).orElseThrow();
        doc.setEffectiveCount(doc.getEffectiveCount() - 1);
        repository.save(doc);
    }
}

