package com.example.axon.application.external.retry;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;

public class ExternalRetryRequestedHandler {
    @EventHandler
    public void on(ExternalRetryRequested event, CommandGateway commandGateway) {
        commandGateway.send(event.command());
    }
}
