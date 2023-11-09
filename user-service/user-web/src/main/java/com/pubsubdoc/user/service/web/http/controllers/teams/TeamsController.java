package com.pubsubdoc.user.service.web.http.controllers.teams;

import com.pubsubdoc.user.service.shared.application.doc.models.team.TeamId;
import com.pubsubdoc.user.service.shared.application.doc.models.user.UserId;
import com.pubsubdoc.user.service.web.app.adaptor.aggregates.team.commands.TeamCreate;
import com.pubsubdoc.user.service.web.app.application.query.user.queries.GetUserDataModel;
import com.pubsubdoc.user.service.web.app.application.query.user.queries.GetUserDataModelResult;
import com.pubsubdoc.user.service.web.http.controllers.teams.exceptions.TeamsControllerOwnerUserNotFoundException;
import com.pubsubdoc.user.service.web.http.models.teams.post.TeamsPostRequest;
import com.pubsubdoc.user.service.web.http.models.teams.post.TeamsPostResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@Tag(description = "Team API", name = "Team API")
@RestController
@RequestMapping("/api/teams")
public record TeamsController(CommandGateway commandGateway, QueryGateway queryGateway) {
    @Operation(summary = "Create a new team.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeamsPostResponse post(TeamsPostRequest request) throws ExecutionException, InterruptedException {
        var result = queryGateway.query(new GetUserDataModel(request.ownerUserId()), GetUserDataModelResult.class)
                .get();
        if (result.userDataModel().isEmpty()) {
            throw new TeamsControllerOwnerUserNotFoundException(request.ownerUserId());
        }

        var command = new TeamCreate(new TeamId(), request.teamName(), new UserId(request.ownerUserId()));
        TeamId teamId = commandGateway.sendAndWait(command);

        return new TeamsPostResponse(teamId.value());
    }
}
