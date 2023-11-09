package com.pubsubdoc.user.service.api.domain.models.team;

import com.pubsubdoc.user.service.shared.application.doc.models.team.TeamId;
import com.pubsubdoc.user.service.shared.application.doc.models.user.UserId;

public record TeamMemberAdded(TeamId teamId, UserId newMemberId) implements TeamEvent {
}
