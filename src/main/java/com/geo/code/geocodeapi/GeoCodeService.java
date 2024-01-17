package com.geo.code.geocodeapi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GeoCodeService {
    private final GeoCodeConfig config;
    @PostConstruct
    GeoCode getGeoCode()throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(
                        "https://api.geoapify.com/v1/geocode/search?text=38%20Upper%20Montagu%20Street%2C%20Westminster%20W1H%201LJ%2C%20United%20Kingdom&apiKey="+config.getApiKey()))
                .build();
        HttpResponse httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        String response = httpResponse.body().toString();
        log.info(httpResponse.body().toString());


        ObjectMapper objectMapper = new ObjectMapper();
        //GeoCode geoCode = objectMapper.readValue(response, GeoCode.class);
        JsonNode root = objectMapper.readTree(response);

            JsonNode features = root.path("features");
            JsonNode properties = features.path("properties");


            String country = properties.path("country").asText();
            String city = properties.path("city").asText();
            String state = properties.path("state").asText();
            String postcode = properties.path("postcode").asText();

            GeoCode geoCode = new GeoCode(country, city, state, postcode);


         //GeoCode geoCode = objectMapper.readValue(response, GeoCode.class);

        log.info("Country | City | State | PostCode");
        log.info(geoCode.getCountry());
        log.info(geoCode.getCity());
        log.info(geoCode.getState());
        log.info(geoCode.getPostcode());

        return geoCode;
    }
}
