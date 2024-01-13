package com.pubsubdoc.payment.service.web.config.axon;

import com.pubsubdoc.payment.service.web.app.adaptor.aggregates.paymentprocess.PaymentProcessAggregate;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.eventhandling.scheduling.quartz.QuartzEventScheduler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.correlation.CorrelationDataProvider;
import org.axonframework.messaging.correlation.MessageOriginProvider;
import org.axonframework.messaging.correlation.MultiCorrelationDataProvider;
import org.axonframework.messaging.correlation.SimpleCorrelationDataProvider;
import org.axonframework.serialization.Serializer;
import org.axonframework.spring.eventhandling.scheduling.quartz.QuartzEventSchedulerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AxonConfiguration {
    @Bean
    public EventSourcingRepository<PaymentProcessAggregate> paymentAggregateEventSourcingRepository(EventStore eventStore) {
        return EventSourcingRepository.builder(PaymentProcessAggregate.class)
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

    @Bean
    public QuartzEventSchedulerFactoryBean quartzEventSchedulerFactoryBean(Serializer serializer) {
        var bean = new QuartzEventSchedulerFactoryBean();
        bean.setEventJobDataBinder(new QuartzEventScheduler.DirectEventJobDataBinder(serializer));

        return bean;
    }
}
