package com.oreilly.restclient.services;

import com.oreilly.restclient.json.GeocodeResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.swing.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GeocoderService {

    public static final String BASE_URL = "https://maps.googleapis.com";
    private static final String KEY = "";

    private final WebClient client = WebClient.create(BASE_URL);

    public GeocodeResponse getLatLong(String... address) {
        String encodedAddress = Stream.of(address)
                .map(component -> URLEncoder.encode(component, StandardCharsets.UTF_8))
                .collect(Collectors.joining(","));
        return client.get()
                .uri(uriBuilder ->
                        uriBuilder.path("/maps/api/geocode/json")
                                .queryParam("address", encodedAddress)
                                .queryParam("key", KEY)
                                .build()
                )
                .retrieve()
                .bodyToMono(GeocodeResponse.class)
                .log()
                .block(Duration.ofSeconds(2));
    }

}
