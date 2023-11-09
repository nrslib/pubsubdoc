package com.pubsubdoc.user.service.web.http.models.users.post;

import java.util.UUID;

public record UsersPostResponse(
        UUID id,
        String name
) {
}
