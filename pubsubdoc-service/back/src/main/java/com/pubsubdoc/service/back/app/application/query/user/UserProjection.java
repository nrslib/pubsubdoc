package com.pubsubdoc.service.back.app.application.query.user;

import com.pubsubdoc.service.back.app.infrastructure.jpa.user.UserDataModel;
import com.pubsubdoc.service.back.app.infrastructure.jpa.user.UserRepository;
import com.pubsubdoc.user.service.api.domain.models.team.TeamMemberAdded;
import com.pubsubdoc.user.service.api.domain.models.user.UserCreated;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.ResetHandler;
import org.springframework.stereotype.Component;

@Component
public class UserProjection {
    private final UserRepository userRepository;

    public UserProjection(UserRepository userRepository) {this.userRepository = userRepository;}

    @ResetHandler
    public void reset() {
        userRepository.deleteAll();
    }

    @EventHandler
    public void on(UserCreated event) {
        var user = new UserDataModel();
        user.setUserId(event.userId().asString());
        user.setName(event.name());

        userRepository.save(user);
    }

    @EventHandler
    public void on(TeamMemberAdded event) {
        var user = userRepository.findById(event.newMemberId().asString()).orElseThrow();
        user.setTeamId(event.teamId().asString());

        userRepository.save(user);
    }
}
