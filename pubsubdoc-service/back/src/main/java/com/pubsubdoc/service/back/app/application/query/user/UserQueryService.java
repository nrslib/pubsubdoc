package com.pubsubdoc.service.back.app.application.query.user;

import com.pubsubdoc.service.back.app.application.query.user.queries.FindUser;
import com.pubsubdoc.service.back.app.application.query.user.queries.FindUserResult;
import com.pubsubdoc.service.back.app.infrastructure.jpa.user.UserRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

@Service
public record UserQueryService(UserRepository userRepository) {
    @QueryHandler
    public FindUserResult handle(FindUser criteria) {
        var maybeUser = userRepository.findById(criteria.userId().toString());

        return new FindUserResult(maybeUser);
    }
}
