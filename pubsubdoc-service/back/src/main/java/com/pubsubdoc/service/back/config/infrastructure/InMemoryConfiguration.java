package com.pubsubdoc.service.back.config.infrastructure;

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
}
