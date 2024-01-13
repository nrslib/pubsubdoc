package com.example.axon.application.config;

import com.example.axon.application.external.retry.ExternalRetryRequestedHandler;
import org.axonframework.springboot.autoconfig.AxonAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
//@ConditionalOnClass(ExternalRetryRequestedHandler.class)
//@ConditionalOnMissingBean(ExternalRetryRequestedHandler.class)
@AutoConfigureAfter(AxonAutoConfiguration.class)
@EnableConfigurationProperties(AxonExtendsProperties.class)
public class AxonExtendsAutoConfiguration {
    @Bean
    public ExternalRetryRequestedHandler externalRetryRequestedHandler() {
        return new ExternalRetryRequestedHandler();
    }
}
