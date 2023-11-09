package com.pubsubdoc.user.service.sdk.api;

import com.pubsubdoc.user.service.sdk.api.protocol.UsersFindResponse;
import com.pubsubdoc.user.service.sdk.api.protocol.UsersGetResponse;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

public record UserServiceApiApiImpl(String host) implements UserServiceApi {
    @Override
    public UsersGetResponse getUser(UUID userId) {
        var restTemplate = new RestTemplate();
        var response = restTemplate.getForEntity(host + "/api/users/" + userId, UsersGetResponse.class);
        return response.getBody();
    }
    @Override
    public UsersFindResponse findUser(String userName) {
        var restTemplate = new RestTemplate();
        var response = restTemplate.getForEntity(host + "/api/users?userName=" + userName, UsersFindResponse.class);

        return response.getBody();
    }
}
