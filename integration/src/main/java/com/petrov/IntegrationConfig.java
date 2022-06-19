package com.petrov;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.MessageChannel;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class IntegrationConfig{

    @Bean
    public MessageChannel receiver(){
        return new DirectChannel();
    }

    @Bean
    public MessageChannel reply() {
        return new DirectChannel();
    }
}
