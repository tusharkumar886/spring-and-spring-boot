package com.oreilly.demo1.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HelloController.class)
class HelloControllerMockMVCTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void autoWiringWorks() {
        assertNotNull(mvc);
    }

    @Test
    void sayHelloDefaultBehaviour() throws Exception {
        this.mvc.perform(get("/hello").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(model().attribute("username", "WORLD"))
                .andExpect(view().name("welcome"));
    }

    @Test
    void sayHelloParam() throws Exception {
        this.mvc.perform(get("/hello").param("name", "John").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(model().attribute("username", "JOHN"))
                .andExpect(view().name("welcome"));
    }
}