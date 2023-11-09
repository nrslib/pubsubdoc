package com.pubsubdoc.user.service.web.http.controllers.teams;

import com.pubsubdoc.user.service.web.http.controllers.teams.exceptions.TeamsControllerOwnerUserNotFoundException;
import com.pubsubdoc.user.service.web.http.models.common.ErrorsResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(basePackageClasses = TeamsController.class)
public class TeamsControllerAdvice {
    @ExceptionHandler(TeamsControllerOwnerUserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsResponse handle(TeamsControllerOwnerUserNotFoundException exception) {
        return ErrorsResponse.apply("400", "Owner user not found.");
    }
}
