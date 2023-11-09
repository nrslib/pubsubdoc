package com.pubsubdoc.user.service.web.app.adaptor.aggregates.team.commands;

import com.pubsubdoc.user.service.shared.application.doc.models.team.TeamId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public interface TeamCommand {
    @TargetAggregateIdentifier
    TeamId teamId();
}
