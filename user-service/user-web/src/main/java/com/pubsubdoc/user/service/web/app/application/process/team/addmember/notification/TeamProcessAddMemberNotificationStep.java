package com.pubsubdoc.user.service.web.app.application.process.team.addmember.notification;

import com.pubsubdoc.user.service.api.domain.models.team.TeamMemberAdded;
import com.pubsubdoc.user.service.web.app.external.notification.protocol.sendmemberadded.NotificationSendMemberAdded;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public record TeamProcessAddMemberNotificationStep(CommandGateway commandGateway) {
    @EventHandler
    public void on(TeamMemberAdded event) {
        commandGateway.send(new NotificationSendMemberAdded(event.teamId(), event.newMemberId()));
    }
}
