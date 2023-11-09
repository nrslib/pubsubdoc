package com.pubsubdoc.service.back.app.application.query.doc.queries;

import com.pubsubdoc.service.back.app.infrastructure.jpa.doc.DocDataModel;

import java.util.Optional;

public record GetDocResult(Optional<DocDataModel> docDataModel) {
}
