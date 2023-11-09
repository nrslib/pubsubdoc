package com.pubsubdoc.doc.service.web.app.adaptor.aggregates.doc.commands.create;

import com.pubsubdoc.doc.service.shared.application.doc.models.doc.DocId;
import com.pubsubdoc.doc.service.web.app.adaptor.aggregates.doc.commands.DocCommand;

public record DocCreate(DocId docId, String body) implements DocCommand {
}
