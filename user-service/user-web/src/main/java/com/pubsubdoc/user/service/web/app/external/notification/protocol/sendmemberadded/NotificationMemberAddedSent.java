package com.pubsubdoc.user.service.web.app.external.notification.protocol.sendmemberadded;

import com.pubsubdoc.user.service.shared.application.doc.models.team.TeamId;

public record NotificationMemberAddedSent(TeamId teamId, String message) {
}
