package com.pubsubdoc.service.back.app.application.query.doc;

import com.pubsubdoc.service.back.app.application.query.doc.queries.GetDoc;
import com.pubsubdoc.service.back.app.application.query.doc.queries.GetDocResult;
import com.pubsubdoc.service.back.app.infrastructure.jpa.doc.DocRepository;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

@Service
public record DocQueryService(QueryGateway queryGateway, DocRepository repository) {
    @QueryHandler
    public GetDocResult handle(GetDoc query) {
        return new GetDocResult(repository.findById(query.docId().toString()));
    }
}
