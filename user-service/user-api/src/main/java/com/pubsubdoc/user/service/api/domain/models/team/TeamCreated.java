package com.pubsubdoc.user.service.api.domain.models.team;

import com.pubsubdoc.user.service.shared.application.doc.models.team.TeamId;
import com.pubsubdoc.user.service.shared.application.doc.models.user.UserId;

public record TeamCreated(TeamId teamId, String teamName, UserId ownerUserId) implements TeamEvent {
}
