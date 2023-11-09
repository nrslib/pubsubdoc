package com.pubsubdoc.service.back.app.application.process.user.requestcreation;

import com.pubsubdoc.user.service.shared.application.doc.models.user.UserId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

public record UserProcessRequestCreation(@TargetAggregateIdentifier UserId userId, String userName) {
}
