package com.pubsubdoc.user.service.api.domain.models.team;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.pubsubdoc.user.service.shared.application.doc.models.team.TeamId;

public record TeamOperationFailed(TeamId teamId, @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS) Object command, String message) implements TeamEvent {
}
