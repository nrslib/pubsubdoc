package com.pubsubdoc.user.service.web.http.controllers.users;

import com.example.appkit.http.exceptions.NotFoundException;
import com.pubsubdoc.user.service.shared.application.doc.models.user.UserId;
import com.pubsubdoc.user.service.web.app.adaptor.aggregates.user.commands.UserCreate;
import com.pubsubdoc.user.service.web.app.application.query.user.queries.FindUserDataModel;
import com.pubsubdoc.user.service.web.app.application.query.user.queries.FindUserDataModelResult;
import com.pubsubdoc.user.service.web.app.application.query.user.queries.GetUserDataModel;
import com.pubsubdoc.user.service.web.app.application.query.user.queries.GetUserDataModelResult;
import com.pubsubdoc.user.service.web.http.models.users.find.UserFindResponse;
import com.pubsubdoc.user.service.web.http.models.users.get.UserGetResponse;
import com.pubsubdoc.user.service.web.http.models.users.post.UsersPostRequest;
import com.pubsubdoc.user.service.web.http.models.users.post.UsersPostResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Tag(description = "User API", name = "User API")
@RestController
@RequestMapping("/api/users")
public record UsersController(CommandGateway commandGateway, QueryGateway queryGateway) {
    @Operation
    @GetMapping("{userId}")
    public UserGetResponse get(@PathVariable UUID userId) throws ExecutionException, InterruptedException {
        var criteria = new GetUserDataModel(userId);
        var result = queryGateway.query(criteria, GetUserDataModelResult.class).get();


        var user = result.userDataModel().orElseThrow(NotFoundException::new);
        return new UserGetResponse(user.getUserId(), user.getName());
    }

    @Operation
    @GetMapping
    public UserFindResponse find(@RequestParam String userName) throws ExecutionException, InterruptedException {
        var criteria = new FindUserDataModel(userName);
        var result = queryGateway.query(criteria, FindUserDataModelResult.class).get();

        return new UserFindResponse(result.userDataModels());
    }

    @Operation(summary = "Create a new user.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsersPostResponse post(@Validated @RequestBody UsersPostRequest request) {
        var command = new UserCreate(new UserId(), request.name());
        UserId userId = commandGateway.sendAndWait(command);

        return new UsersPostResponse(userId.value(), request.name());
    }
}
