package com.pubsubdoc.user.service.sdk.api;

import com.pubsubdoc.user.service.sdk.api.protocol.UsersFindResponse;
import com.pubsubdoc.user.service.sdk.api.protocol.UsersGetResponse;

import java.util.UUID;

public interface UserServiceApi {
    UsersGetResponse getUser(UUID userId);

    UsersFindResponse findUser(String userName);
}
