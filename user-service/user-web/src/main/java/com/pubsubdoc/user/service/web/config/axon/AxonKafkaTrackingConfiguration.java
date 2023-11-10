package com.pubsubdoc.user.service.web.config.axon;

import com.pubsubdoc.user.service.web.app.application.process.user.create.creation.UserProcessCreateCreationStep;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.extensions.kafka.eventhandling.consumer.streamable.StreamableKafkaMessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnExpression("!${application.disable-kafka:false} and '${axon.kafka.consumer.event-processor-mode}' == 'tracking'")
public class AxonKafkaTrackingConfiguration {
    @Autowired
    public void registerProcessor(EventProcessingConfigurer configurer, StreamableKafkaMessageSource<String, byte[]> streamableKafkaMessageSource) {
        configurer.registerTrackingEventProcessor(
                UserProcessCreateCreationStep.class.getPackageName(),
                c -> streamableKafkaMessageSource
        );
    }
}
