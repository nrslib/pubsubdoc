package com.pubsubdoc.user.service.web.http.controllers.teamsmemberships;

import com.pubsubdoc.user.service.api.domain.models.team.TeamEvent;
import com.pubsubdoc.user.service.shared.application.doc.models.team.TeamId;
import com.pubsubdoc.user.service.shared.application.doc.models.user.UserId;
import com.pubsubdoc.user.service.web.app.adaptor.aggregates.team.commands.TeamAddMember;
import com.pubsubdoc.user.service.web.app.application.domain.models.team.errors.TeamError;
import com.pubsubdoc.user.service.web.http.controllers.teamsmemberships.exceptions.TeamsMembersControllerMemberFullException;
import fj.data.Either;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(description = "Team Membership API", name = "Team Membership API")
@RestController
@RequestMapping("/api/teams/{teamId}/memberships")
public record TeamsMembershipsController(CommandGateway commandGateway) {
    @Operation(summary = "Update a team membership.")
    @PutMapping("/{userId}")
    public void put(@PathVariable UUID teamId, @PathVariable UUID userId) {
        var command = new TeamAddMember(new TeamId(teamId), new UserId(userId));
        Either<TeamError, TeamEvent> result = commandGateway.sendAndWait(command);

        if (result.isLeft()) {
            throw new TeamsMembersControllerMemberFullException(userId);
        }
    }
}
