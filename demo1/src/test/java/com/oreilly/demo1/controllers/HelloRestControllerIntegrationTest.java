package com.oreilly.demo1.controllers;

import com.oreilly.demo1.json.Greeting;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloRestControllerIntegrationTest {

    @Test
    void greetingWithName(@Autowired TestRestTemplate testRestTemplate) {
        testRestTemplate.postForLocation("/rest/{name}", null, "John");
        Greeting response = testRestTemplate.getForObject("/rest?name=John", Greeting.class);
        assertEquals("Hello, John!", response.message());
    }

    @Test
    void greetingWithoutName(@Autowired TestRestTemplate testRestTemplate) {
        ResponseEntity<Greeting> entity = testRestTemplate.getForEntity("/rest", Greeting.class);
        assertEquals(OK, entity.getStatusCode());
        assertEquals(APPLICATION_JSON, entity.getHeaders().getContentType());
        Greeting response = entity.getBody();
        if (response != null) {
            assertEquals("Hello, World!", response.message());
        }
    }
}
