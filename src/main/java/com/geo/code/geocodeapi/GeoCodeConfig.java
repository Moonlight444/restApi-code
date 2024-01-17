package com.geo.code.geocodeapi;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix="geo.code")
@Data
@Component
public class GeoCodeConfig {
    String apiKey;
}
