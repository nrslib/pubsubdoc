package com.pubsubdoc.back.service.api.process.user.requestcreation;


import com.pubsubdoc.user.service.shared.application.doc.models.user.UserId;

public record UserProcessRequestCreationRequested(UserId userId, String userName) implements UserProcessRequestCreationEvent {
}
