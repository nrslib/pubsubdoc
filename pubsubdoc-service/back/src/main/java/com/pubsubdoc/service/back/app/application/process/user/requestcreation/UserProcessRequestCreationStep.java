package com.pubsubdoc.service.back.app.application.process.user.requestcreation;

import com.pubsubdoc.back.service.api.process.user.requestcreation.UserProcessRequestCreationRequested;
import com.pubsubdoc.user.service.sdk.api.UserServiceApi;
import com.pubsubdoc.user.service.shared.application.doc.models.user.UserId;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.stereotype.Component;

@Component
public record UserProcessRequestCreationStep(UserServiceApi userServiceApi, EventGateway eventGateway) {
    @CommandHandler
    public UserId on(UserProcessRequestCreation command) {
        // If you need checking duplicated.
        var result = userServiceApi.findUser(command.userName());
        var target = result.users().stream().findFirst();

        var event = new UserProcessRequestCreationRequested(command.userId(), command.userName());
        eventGateway.publish(event);

        return command.userId();
    }
}
