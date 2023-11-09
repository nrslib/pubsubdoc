package com.pubsubdoc.service.back.http.controllers.users;

import com.pubsubdoc.service.back.app.application.process.user.requestcreation.UserProcessRequestCreation;
import com.pubsubdoc.service.back.app.application.query.user.queries.FindUser;
import com.pubsubdoc.service.back.app.application.query.user.queries.FindUserResult;
import com.pubsubdoc.service.back.http.models.users.get.UsersGetResponse;
import com.pubsubdoc.service.back.http.models.users.get.UsersResponseModel;
import com.pubsubdoc.service.back.http.models.users.post.UsersPostRequest;
import com.pubsubdoc.service.back.http.models.users.post.UsersPostResponse;
import com.pubsubdoc.user.service.shared.application.doc.models.user.UserId;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.MetaData;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Tag(description = "User API", name = "User API")
@RestController
@RequestMapping("/api/users")
public record UsersController(CommandGateway commandGateway, QueryGateway queryGateway) {
    @GetMapping("{userId}")
    public UsersGetResponse get(@PathVariable UUID userId) throws ExecutionException, InterruptedException {
        var criteria = new FindUser(userId);
        var result = queryGateway.query(criteria, FindUserResult.class).get();
        var user = result.userDataModel()
                .map(it -> new UsersResponseModel(userId, it.getName(), it.getTeamId()))
                .orElse(null);

        return new UsersGetResponse(user);
    }

    @PostMapping
    public UsersPostResponse post(UsersPostRequest request) {
        var command = new UserProcessRequestCreation(new UserId(), request.userName());
        UserId userId = commandGateway.sendAndWait(command, new MetaData(Map.of("processId", UUID.randomUUID())));

        return new UsersPostResponse(userId.value());
    }
}
