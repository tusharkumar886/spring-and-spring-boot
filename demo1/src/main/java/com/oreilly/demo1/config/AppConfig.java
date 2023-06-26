package com.oreilly.demo1.config;

import com.oreilly.demo1.json.Greeting;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class AppConfig {

    @Bean
//    @Scope("prototype")
//    @Lazy
    public Greeting defaultGreeting(){
        return new Greeting("Hello, World!");
    }

    @Bean
    public RestTemplate getRestTemplate(RestTemplateBuilder builder) {
        return builder.setConnectTimeout(Duration.ofSeconds(2)).build();
    }
}
