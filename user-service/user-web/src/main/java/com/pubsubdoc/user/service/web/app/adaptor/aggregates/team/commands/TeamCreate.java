package com.pubsubdoc.user.service.web.app.adaptor.aggregates.team.commands;

import com.pubsubdoc.user.service.shared.application.doc.models.team.TeamId;
import com.pubsubdoc.user.service.shared.application.doc.models.user.UserId;

public record TeamCreate(TeamId teamId, String teamName, UserId ownerUserId) implements TeamCommand {
}
