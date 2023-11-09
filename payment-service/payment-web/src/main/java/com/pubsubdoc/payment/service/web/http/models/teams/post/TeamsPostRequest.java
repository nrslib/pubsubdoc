package com.pubsubdoc.payment.service.web.http.models.teams.post;

import java.util.UUID;

public record TeamsPostRequest(String teamName, UUID ownerUserId) {
}
