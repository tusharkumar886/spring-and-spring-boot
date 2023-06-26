package com.oreilly.demo1.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloControllerUnitTest {

    @Test
    void sayHello() {
        HelloController controller = new HelloController();
        Model model = new BindingAwareModelMap();
        String result = controller.sayHello("Hello", model);
        assertAll(
                () -> assertEquals("welcome", result),
                () -> assertEquals("HELLO", model.asMap().get("username"))
        );
    }
}