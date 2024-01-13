package com.example.axon.application.external.retry;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

public record ExternalRetryRequested(@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS) RetryableCommand command) {
}
