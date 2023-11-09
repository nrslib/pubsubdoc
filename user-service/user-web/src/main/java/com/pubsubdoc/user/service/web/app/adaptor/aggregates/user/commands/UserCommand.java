package com.pubsubdoc.user.service.web.app.adaptor.aggregates.user.commands;

import com.pubsubdoc.user.service.shared.application.doc.models.user.UserId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public interface UserCommand {
    @TargetAggregateIdentifier
    UserId userId();
}
