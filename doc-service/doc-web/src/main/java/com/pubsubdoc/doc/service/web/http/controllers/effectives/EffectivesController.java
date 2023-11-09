package com.pubsubdoc.doc.service.web.http.controllers.effectives;

import com.pubsubdoc.doc.service.shared.application.doc.models.doc.DocId;
import com.pubsubdoc.doc.service.web.app.adaptor.aggregates.effective.commands.EffectiveMark;
import com.pubsubdoc.doc.service.web.app.adaptor.aggregates.effective.commands.EffectiveUnmark;
import com.pubsubdoc.doc.service.web.http.models.effectives.post.EffectivesPostRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(description = "Effective API", name = "Effective API")
@RestController
@RequestMapping("/api/effectives")
public record EffectivesController(CommandGateway commandGateway) {
    @Operation(summary = "Effective.")
    @PostMapping("/mark")
    public void effectivePost(@Validated @RequestBody EffectivesPostRequest request) {
        var command = new EffectiveMark(new DocId(request.docId()), request.userId());
        commandGateway.sendAndWait(command);
    }

    @Operation(summary = "Effective.")
    @PostMapping("/unmark")
    public void effectiveDelete(@Validated @RequestBody EffectivesPostRequest request) {
        var command = new EffectiveUnmark(new DocId(request.docId()), request.userId());
        commandGateway.sendAndWait(command);
    }
}
