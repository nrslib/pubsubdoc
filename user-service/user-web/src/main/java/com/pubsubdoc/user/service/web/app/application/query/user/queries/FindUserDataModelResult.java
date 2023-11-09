package com.pubsubdoc.user.service.web.app.application.query.user.queries;

import com.pubsubdoc.user.service.web.app.infrastructure.jpa.user.UserDataModel;

import java.util.List;
import java.util.Optional;

public record FindUserDataModelResult(List<UserDataModel> userDataModels) {
}
