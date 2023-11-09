package com.pubsubdoc.user.service.web.app.adaptor.aggregates.user;

import com.example.axon.application.adaptor.AbstractAggregate;
import com.pubsubdoc.user.service.api.domain.models.user.UserCreated;
import com.pubsubdoc.user.service.api.domain.models.user.UserEvent;
import com.pubsubdoc.user.service.shared.application.doc.models.user.UserId;
import com.pubsubdoc.user.service.web.app.adaptor.aggregates.user.commands.UserCreate;
import com.pubsubdoc.user.service.web.app.application.domain.models.user.User;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class UserAggregate extends AbstractAggregate<User, UserId, UserEvent> {

    @Override
    protected UserId getAggregateRootId(User aggregate) {
        if (aggregate != null) {
            return aggregate.userId();
        } else {
            return null;
        }
    }
    @Override
    protected User newAggregateRootByEvent(UserEvent event) {
        return User.applyEvent((UserCreated) event);
    }
    @Override
    protected boolean isConstructEvent(UserEvent event) {
        return event instanceof UserCreated;
    }

    protected UserAggregate() {}

    @CommandHandler
    public UserAggregate(UserCreate command) {
        var event = User.create(command.userId(), command.name());
        apply(event);
    }
}
