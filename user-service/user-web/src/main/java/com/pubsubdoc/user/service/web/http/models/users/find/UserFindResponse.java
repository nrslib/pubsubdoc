package com.pubsubdoc.user.service.web.http.models.users.find;

import com.pubsubdoc.user.service.web.app.infrastructure.jpa.user.UserDataModel;

import java.util.List;

public record UserFindResponse(List<UserDataModel> users) {
}
