package com.pubsubdoc.service.back.config.axon;

import com.pubsubdoc.service.back.app.application.query.doc.DocProjection;
import com.pubsubdoc.service.back.app.application.query.user.UserProjection;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.extensions.kafka.eventhandling.consumer.streamable.StreamableKafkaMessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConditionalOnExpression("!${application.disable-kafka:false} and '${axon.kafka.consumer.event-processor-mode}' == 'tracking'")
public class AxonKafkaTrackingConfiguration {
    @Autowired
    public void registerProcessor(EventProcessingConfigurer configurer, StreamableKafkaMessageSource<String, byte[]> streamableKafkaMessageSource) {
        var processorNames = List.of(
                DocProjection.class.getPackageName(),
                UserProjection.class.getPackageName()
        );

        processorNames.forEach(it ->
                configurer.registerTrackingEventProcessor(
                        it,
                        c -> streamableKafkaMessageSource
                ));
    }
}
