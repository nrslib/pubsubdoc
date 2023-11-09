package com.pubsubdoc.doc.service.web.app.adaptor.aggregates.effective.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public interface EffectiveCommand {
    @TargetAggregateIdentifier
    String getIdentifier();
}
