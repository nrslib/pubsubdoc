package com.pubsubdoc.user.service.sdk.api;

import com.pubsubdoc.user.service.sdk.api.protocol.UsersFindResponse;
import com.pubsubdoc.user.service.sdk.api.protocol.UsersGetResponse;

import java.util.List;
import java.util.UUID;

public class InMemoryUserServiceApi implements UserServiceApi {
    @Override
    public UsersGetResponse getUser(UUID userId) {
        return new UsersGetResponse(userId, "", null);
    }
    @Override
    public UsersFindResponse findUser(String userName) {
        return new UsersFindResponse(List.of());
    }
}
