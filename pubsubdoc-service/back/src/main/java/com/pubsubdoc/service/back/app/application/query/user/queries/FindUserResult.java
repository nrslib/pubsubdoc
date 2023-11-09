package com.pubsubdoc.service.back.app.application.query.user.queries;

import com.pubsubdoc.service.back.app.infrastructure.jpa.user.UserDataModel;

import java.util.Optional;

public record FindUserResult(Optional<UserDataModel> userDataModel) {
}
