package com.example.triptalk.global.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "amadeus")
public class AmadeusProperties {
    private String apiKey;
    private String apiSecret;
    private String baseUrl = "https://test.api.amadeus.com";
}

