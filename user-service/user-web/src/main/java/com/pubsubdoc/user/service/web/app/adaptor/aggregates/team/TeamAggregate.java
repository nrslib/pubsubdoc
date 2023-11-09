package com.pubsubdoc.user.service.web.app.adaptor.aggregates.team;

import com.example.axon.application.adaptor.AbstractAggregate;
import com.pubsubdoc.user.service.api.domain.models.team.TeamCreated;
import com.pubsubdoc.user.service.api.domain.models.team.TeamEvent;
import com.pubsubdoc.user.service.api.domain.models.team.TeamOperationFailed;
import com.pubsubdoc.user.service.shared.application.doc.models.team.TeamId;
import com.pubsubdoc.user.service.web.app.adaptor.aggregates.team.commands.TeamAddMember;
import com.pubsubdoc.user.service.web.app.adaptor.aggregates.team.commands.TeamCommand;
import com.pubsubdoc.user.service.web.app.adaptor.aggregates.team.commands.TeamCreate;
import com.pubsubdoc.user.service.web.app.application.domain.models.team.Team;
import com.pubsubdoc.user.service.web.app.application.domain.models.team.errors.TeamError;
import fj.data.Either;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class TeamAggregate extends AbstractAggregate<Team, TeamId, TeamEvent> {
    @Override
    protected TeamId getAggregateRootId(Team aggregate) {
        if (aggregate != null) {
            return aggregate.teamId();
        } else {
            return null;
        }
    }
    @Override
    protected Team newAggregateRootByEvent(TeamEvent event) {
        return Team.applyEvent((TeamCreated) event);
    }
    @Override
    protected boolean isConstructEvent(TeamEvent event) {
        return event instanceof TeamCreated;
    }

    @CommandHandler
    public TeamAggregate(TeamCreate command) {
        var event = Team.create(command.teamId(), command.teamName(), command.ownerUserId());
        apply(event);
    }

    protected TeamAggregate() {}

    @CommandHandler
    public Either<TeamError, TeamEvent> handle(TeamAddMember command) {
        var result = apply(it -> it.join(command.newMemberId()),
                it -> applyFail(command, it));

        return result;
    }

    private void applyFail(TeamCommand command, TeamError error) {
        var failEvent = new TeamOperationFailed(command.teamId(), command, error.toString());
        apply(failEvent);
    }
}
