package com.pubsubdoc.payment.service.web.app.application.query.user;

import com.pubsubdoc.payment.service.web.app.application.query.user.queries.GetUser;
import com.pubsubdoc.payment.service.web.app.application.query.user.queries.GetUserResult;
import com.pubsubdoc.user.service.sdk.api.UserServiceApi;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Service
public record UserQueryService(UserServiceApi userServiceApi) {
    @QueryHandler
    public GetUserResult handle(GetUser criteria) {
        try {
            var response = userServiceApi.getUser(criteria.userId());

            return new GetUserResult(Optional.of(response));
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatusCode.valueOf(404)) {
                return new GetUserResult(Optional.empty());
            } else {
                throw e;
            }
        }
    }
}
