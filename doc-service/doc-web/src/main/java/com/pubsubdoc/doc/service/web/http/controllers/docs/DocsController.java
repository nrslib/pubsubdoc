package com.pubsubdoc.doc.service.web.http.controllers.docs;

import com.pubsubdoc.doc.service.shared.application.doc.models.doc.DocId;
import com.pubsubdoc.doc.service.web.app.adaptor.aggregates.doc.commands.create.DocCreate;
import com.pubsubdoc.doc.service.web.app.adaptor.aggregates.doc.commands.create.DocUpdate;
import com.pubsubdoc.doc.service.web.http.models.docs.post.DocsPostRequest;
import com.pubsubdoc.doc.service.web.http.models.docs.post.DocsPostResponse;
import com.pubsubdoc.doc.service.web.http.models.docs.put.DocsPutRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(description = "Doc API", name = "Doc API")
@RestController
@RequestMapping("/api/docs")
public record DocsController(CommandGateway commandGateway) {

    @Operation(summary = "Creating a new doc.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DocsPostResponse post(@Validated @RequestBody DocsPostRequest request) {
        var command = new DocCreate(new DocId(), request.body());
        DocId docId = commandGateway.sendAndWait(command);

        return new DocsPostResponse(docId.value());
    }

    @Operation(summary = "Modify a doc.")
    @PutMapping("/{docId}")
    public void put(@PathVariable UUID docId, @Validated @RequestBody DocsPutRequest request) {
        var command = new DocUpdate(new DocId(docId), request.body());
        commandGateway.sendAndWait(command);
    }
}
