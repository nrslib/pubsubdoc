package com.pubsubdoc.payment.service.web.config.infrastructure;

import com.pubsubdoc.payment.service.web.app.infrastructure.api.credit.CreditApi;
import com.pubsubdoc.payment.service.web.app.infrastructure.inmem.api.credit.InMemoryCreditApi;
import com.pubsubdoc.user.service.sdk.api.InMemoryUserServiceApi;
import com.pubsubdoc.user.service.sdk.api.UserServiceApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("inmem")
public class InMemoryConfiguration {
    @Bean
    public UserServiceApi userService() {
        return new InMemoryUserServiceApi();
    }

    @Bean
    public CreditApi creditApi() {
        return new InMemoryCreditApi();
    }
}
