package com.oreilly.restclient.services;

import com.oreilly.restclient.json.AstroResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

// Typical 3-layer architecture for Java apps:
// - Presentation Layer (controllers and views)
// - Business Layer (business logic and transaction boundaries)
// - Persistence Layer (converts Java class to DB tables and back)
@Service
public class AstroService {
    private static final String BASE_URL = "http://api.open-notify.org";
    private final WebClient client = WebClient.create(BASE_URL);
    private final RestTemplate restTemplate;

    @Autowired
    public AstroService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.setConnectTimeout(Duration.ofSeconds(2)).build();
    }

    public String getAstronauts() {
        return client.get()
                .uri("/astros.json")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public AstroResponse getAstroResponse() {
        return client.get()
                .uri("/astros.json")
                .retrieve()
                .bodyToMono(AstroResponse.class)
                .block(Duration.ofSeconds(2));
    }

    public AstroResponse getAstroResponseAdv() {
        return client.get()
                .uri(uriBuilder -> uriBuilder.path("/astros.json").build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(AstroResponse.class)
                .log()
                .block(Duration.ofSeconds(2));
    }

    public AstroResponse getAstroResponseRestTemplate() {
        return restTemplate.getForObject(BASE_URL + "/astros.json", AstroResponse.class);
    }
}
