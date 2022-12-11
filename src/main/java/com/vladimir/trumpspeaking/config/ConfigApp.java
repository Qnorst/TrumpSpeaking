package com.vladimir.trumpspeaking.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("application.properties")
@ComponentScan
public class ConfigApp {
    @Value("${telegram.token}")
    private String token;

    @Value("${telegram.nameBot}")
    private String nameBot;

    public String getToken() {
        return token;
    }

    public String getNameBot() {
        return nameBot;
    }
}
