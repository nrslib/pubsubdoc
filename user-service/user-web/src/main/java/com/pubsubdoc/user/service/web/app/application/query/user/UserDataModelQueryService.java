package com.pubsubdoc.user.service.web.app.application.query.user;

import com.pubsubdoc.user.service.web.app.application.query.user.queries.FindUserDataModel;
import com.pubsubdoc.user.service.web.app.application.query.user.queries.FindUserDataModelResult;
import com.pubsubdoc.user.service.web.app.application.query.user.queries.GetUserDataModel;
import com.pubsubdoc.user.service.web.app.application.query.user.queries.GetUserDataModelResult;
import com.pubsubdoc.user.service.web.app.infrastructure.jpa.user.UserDataModel;
import com.pubsubdoc.user.service.web.app.infrastructure.jpa.user.UserDataRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public record UserDataModelQueryService(UserDataRepository userDataRepository) {
    @QueryHandler
    public GetUserDataModelResult handle(GetUserDataModel criteria) {
        return userDataRepository.findById(criteria.userId().toString())
                .map(it -> new GetUserDataModelResult(Optional.of(it)))
                .orElseGet(() -> new GetUserDataModelResult(Optional.empty()));
    }

    @QueryHandler
    public FindUserDataModelResult handle(FindUserDataModel criteria) {
        var spec = Specification.where(
                equalUserName(criteria.userName())
        );
        var users = userDataRepository.findAll(spec);

        return new FindUserDataModelResult(users);
    }

    private Specification<UserDataModel> equalUserName(String userName) {
        if (userName == null) {
            return null;
        }
        return (root, query, cb) ->
                cb.equal(root.get("name"), userName);

    }
}
