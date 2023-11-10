package com.pubsubdoc.user.service.web.app.application.process.user.create.creation;

import com.pubsubdoc.back.service.api.process.user.requestcreation.UserProcessRequestCreationRequested;
import com.pubsubdoc.user.service.web.app.adaptor.aggregates.user.commands.UserCreate;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public record UserProcessCreateCreationStep(CommandGateway commandGateway) {
    @EventHandler
    public void on(UserProcessRequestCreationRequested event) {
        var command = new UserCreate(event.userId(), event.userName());
        commandGateway.send(command);
    }
}
