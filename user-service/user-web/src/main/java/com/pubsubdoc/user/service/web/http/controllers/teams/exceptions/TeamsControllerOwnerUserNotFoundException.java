package com.pubsubdoc.user.service.web.http.controllers.teams.exceptions;

import java.util.UUID;

public class TeamsControllerOwnerUserNotFoundException extends RuntimeException {
    public TeamsControllerOwnerUserNotFoundException(UUID ownerUserId) {
        super();
    }
}
