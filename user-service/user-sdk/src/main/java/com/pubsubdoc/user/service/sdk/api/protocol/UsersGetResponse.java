package com.pubsubdoc.user.service.sdk.api.protocol;

import java.util.UUID;

public record UsersGetResponse(
        UUID userId,
        String userName,
        String teamId
) {
}