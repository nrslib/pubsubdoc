package com.example.axon.application.external.retry;

import java.util.Map;

public record CommandMeta(String className, Map<String, String> properties) {
}
