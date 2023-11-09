package com.pubsubdoc.user.service.web.app.external.notification;

import com.pubsubdoc.user.service.web.app.external.notification.protocol.sendmemberadded.NotificationMemberAddedSent;
import com.pubsubdoc.user.service.web.app.external.notification.protocol.sendmemberadded.NotificationSendMemberAdded;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.stereotype.Service;

@Service
public record NotificationService(EventGateway eventGateway) {
    @CommandHandler
    public void handle(NotificationSendMemberAdded command) {
        // Call http client to send notification.

        eventGateway.publish(new NotificationMemberAddedSent(command.teamId(), "encrypted message."));
    }
}
