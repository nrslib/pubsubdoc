package com.pubsubdoc.user.service.web.app.external.notification.protocol.sendmemberadded;

import com.pubsubdoc.user.service.shared.application.doc.models.team.TeamId;
import com.pubsubdoc.user.service.shared.application.doc.models.user.UserId;

public record NotificationSendMemberAdded(TeamId teamId, UserId userId) {
}
