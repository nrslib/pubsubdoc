package com.pubsubdoc.service.back.config.axon;

import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.correlation.CorrelationDataProvider;
import org.axonframework.messaging.correlation.MessageOriginProvider;
import org.axonframework.messaging.correlation.MultiCorrelationDataProvider;
import org.axonframework.messaging.correlation.SimpleCorrelationDataProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AxonConfiguration {
    @Bean
    public CorrelationDataProvider processIdCorrelationDataProvider() {
        return new MultiCorrelationDataProvider<CommandMessage<?>>(
                List.of(
                        new SimpleCorrelationDataProvider("processId"),
                        new MessageOriginProvider()
                )
        );
    }
}
