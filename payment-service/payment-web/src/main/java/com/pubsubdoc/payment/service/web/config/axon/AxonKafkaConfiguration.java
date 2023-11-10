package com.pubsubdoc.payment.service.web.config.axon;

import org.axonframework.extensions.kafka.KafkaProperties;
import org.axonframework.extensions.kafka.eventhandling.KafkaMessageConverter;
import org.axonframework.extensions.kafka.eventhandling.consumer.ConsumerFactory;
import org.axonframework.extensions.kafka.eventhandling.consumer.Fetcher;
import org.axonframework.extensions.kafka.eventhandling.consumer.streamable.KafkaEventMessage;
import org.axonframework.extensions.kafka.eventhandling.consumer.streamable.SortedKafkaMessageBuffer;
import org.axonframework.extensions.kafka.eventhandling.consumer.streamable.StreamableKafkaMessageSource;
import org.axonframework.serialization.Serializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConditionalOnExpression("!${application.disable-kafka:false} and '${axon.kafka.consumer.event-processor-mode}' == 'tracking'")
public class AxonKafkaConfiguration {
    @Value("${application.kafka.topics}")
    private List<String> topics;

    @Bean
    public StreamableKafkaMessageSource<String, byte[]> streamableKafkaMessageSource(
            ConsumerFactory<String, byte[]> kafkaConsumerFactory,
            Fetcher<String, byte[], KafkaEventMessage> kafkaFetcher,
            KafkaMessageConverter<String, byte[]> kafkaMessageConverter,
            KafkaProperties properties,
            Serializer serializer
    ) {
        return StreamableKafkaMessageSource.<String, byte[]>builder()
                .topics(topics)
                .consumerFactory(kafkaConsumerFactory)
                .fetcher(kafkaFetcher)
                .messageConverter(kafkaMessageConverter)
                .bufferFactory(
                        () -> new SortedKafkaMessageBuffer<>(
                                properties.getFetcher().getBufferSize()
                        )
                )
                .serializer(serializer)
//                .serializer(SecureXStreamSerializer.get())
                .build();
    }
}
