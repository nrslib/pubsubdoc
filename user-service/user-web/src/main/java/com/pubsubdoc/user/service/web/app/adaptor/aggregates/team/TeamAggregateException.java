package com.pubsubdoc.user.service.web.app.adaptor.aggregates.team;

import com.pubsubdoc.user.service.web.app.application.domain.models.team.errors.TeamError;

public class TeamAggregateException extends RuntimeException {
    public TeamAggregateException(TeamError error, String message) {
        super(message);
    }
}
