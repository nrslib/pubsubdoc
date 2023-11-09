package com.pubsubdoc.service.back.http.models.users.get;

import java.util.UUID;

public record UsersResponseModel(
        UUID userId,
        String userName,
        String teamId
) {
}
