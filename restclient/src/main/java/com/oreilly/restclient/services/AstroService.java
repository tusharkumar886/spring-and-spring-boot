package com.oreilly.restclient.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AstroService {
    private WebClient client;

    public AstroService(WebClient.Builder builder) {
        this.client = builder.baseUrl("http://api.open-notify.org").build();
    }

    public void getAstroResponse(){
        
    }
}
