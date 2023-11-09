package com.pubsubdoc.payment.service.web.app.application.query.user.queries;

import com.pubsubdoc.user.service.sdk.api.protocol.UsersGetResponse;

import java.util.Optional;

public record GetUserResult(Optional<UsersGetResponse> userDataModel) {
}
