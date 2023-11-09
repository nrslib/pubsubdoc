package com.pubsubdoc.user.service.web.http.controllers.teamsmemberships;


import com.pubsubdoc.user.service.web.http.controllers.teamsmemberships.exceptions.TeamsMembersControllerMemberFullException;
import com.pubsubdoc.user.service.web.http.models.common.ErrorsResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(basePackageClasses = TeamsMembershipsController.class)
public class TeamsMembershipsControllerAdvice {
    @ExceptionHandler(TeamsMembersControllerMemberFullException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsResponse handle(TeamsMembersControllerMemberFullException exception) {
        return ErrorsResponse.apply("400", "Member is full.");
    }
}
