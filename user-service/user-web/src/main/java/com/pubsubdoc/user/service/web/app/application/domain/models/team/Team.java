package com.pubsubdoc.user.service.web.app.application.domain.models.team;

import com.example.appkit.application.basic.EventDrivenAggregateRoot;
import com.pubsubdoc.user.service.api.domain.models.team.TeamCreated;
import com.pubsubdoc.user.service.api.domain.models.team.TeamEvent;
import com.pubsubdoc.user.service.api.domain.models.team.TeamMemberAdded;
import com.pubsubdoc.user.service.api.domain.models.team.TeamOperationFailed;
import com.pubsubdoc.user.service.shared.application.doc.models.team.TeamId;
import com.pubsubdoc.user.service.shared.application.doc.models.user.UserId;
import com.pubsubdoc.user.service.web.app.application.domain.models.team.errors.TeamError;
import com.pubsubdoc.user.service.web.app.application.domain.models.team.errors.TeamMemberFullError;
import fj.data.Either;

import java.util.ArrayList;
import java.util.List;

public record Team(TeamId teamId, UserId ownerUserId, List<UserId> memberUserIds) implements EventDrivenAggregateRoot<TeamEvent> {
    public static TeamCreated create(TeamId teamId, String teamName, UserId ownerUserId) {
        return new TeamCreated(teamId, teamName, ownerUserId);
    }

    public static Team applyEvent(TeamCreated event) {
        return new Team(event.teamId(), event.ownerUserId(), List.of());
    }

    public Either<TeamError, TeamEvent> join(UserId memberId) {
        if (memberUserIds.size() >= 3) {
            return Either.left(new TeamMemberFullError());
        } else {
            return Either.right(new TeamMemberAdded(teamId, memberId));
        }
    }

    @Override
    public EventDrivenAggregateRoot<TeamEvent> applyEvent(TeamEvent event) {
        return switch (event) {
            case TeamCreated __ -> new Team(teamId, ownerUserId, memberUserIds);
            case TeamMemberAdded teamMemberAdded -> {
                var updatedList = new ArrayList(memberUserIds);
                updatedList.add(teamMemberAdded.newMemberId());
                yield new Team(teamId, ownerUserId, updatedList);
            }
            case TeamOperationFailed __ -> this;
            default -> throw new IllegalStateException("Unexpected value: " + event);
        };
    }
}