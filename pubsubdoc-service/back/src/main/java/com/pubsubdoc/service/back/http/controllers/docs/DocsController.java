package com.pubsubdoc.service.back.http.controllers.docs;


import com.example.appkit.http.exceptions.NotFoundException;
import com.pubsubdoc.service.back.app.application.query.doc.DocProjection;
import com.pubsubdoc.service.back.app.application.query.doc.queries.GetDoc;
import com.pubsubdoc.service.back.app.application.query.doc.queries.GetDocResult;
import com.pubsubdoc.service.back.app.infrastructure.jpa.doc.DocRepository;
import com.pubsubdoc.service.back.http.models.docs.get.DocsGetResponse;
import org.axonframework.config.EventProcessingConfiguration;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.TrackingEventProcessor;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/docs")
public record DocsController(QueryGateway queryGateway, EventProcessingConfiguration eventProcessingConfiguration) {
    @GetMapping("{docsId}")
    public DocsGetResponse get(@PathVariable UUID docsId) throws ExecutionException, InterruptedException {
        var query = new GetDoc(docsId);
        var result = queryGateway.query(query, GetDocResult.class).get();

        var docDataModel = result.docDataModel().orElseThrow(NotFoundException::new);
        return new DocsGetResponse(docsId, docDataModel.getBody(), docDataModel.getEffectiveCount());
    }

    @PostMapping("reset")
    public void reset() {
        eventProcessingConfiguration.eventProcessor(DocProjection.class.getPackageName(), TrackingEventProcessor.class)
                .ifPresent(it -> {
                    it.shutDown();
                    it.resetTokens();
                    it.start();
                });
    }
}
