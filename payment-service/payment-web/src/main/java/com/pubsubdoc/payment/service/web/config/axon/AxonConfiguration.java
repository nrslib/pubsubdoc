package com.pubsubdoc.payment.service.web.config.axon;

import com.pubsubdoc.payment.service.web.app.adaptor.aggregates.payment.PaymentAggregate;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
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
    public EventSourcingRepository<PaymentAggregate> paymentAggregateEventSourcingRepository(EventStore eventStore) {
        return EventSourcingRepository.builder(PaymentAggregate.class)
                .eventStore(eventStore)
                .build();
    }

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
