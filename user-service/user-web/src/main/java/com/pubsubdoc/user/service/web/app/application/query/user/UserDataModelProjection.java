package com.pubsubdoc.user.service.web.app.application.query.user;

import com.pubsubdoc.user.service.api.domain.models.user.UserCreated;
import com.pubsubdoc.user.service.web.app.infrastructure.jpa.user.UserDataModel;
import com.pubsubdoc.user.service.web.app.infrastructure.jpa.user.UserDataRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public record UserDataModelProjection(UserDataRepository userDataRepository) {
    @EventHandler
    public void on(UserCreated event) {
        var userDataModel = new UserDataModel();
        userDataModel.setUserId(event.userId().asString());
        userDataModel.setName(event.name());

        userDataRepository.save(userDataModel);
    }
}
