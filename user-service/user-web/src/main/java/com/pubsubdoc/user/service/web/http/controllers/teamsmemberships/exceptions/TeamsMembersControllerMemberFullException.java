package com.pubsubdoc.user.service.web.http.controllers.teamsmemberships.exceptions;

import java.util.UUID;

public class TeamsMembersControllerMemberFullException extends RuntimeException {
    public TeamsMembersControllerMemberFullException(UUID teamId) {
        super();
    }
}
