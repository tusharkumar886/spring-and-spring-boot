package com.oreilly.demo1.controllers;

import com.oreilly.demo1.json.Greeting;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloRestController {

    private static final Map<String, Greeting> greetingMap = new HashMap<>();

    static {
        greetingMap.put("World", new Greeting("Hello, World!"));
    }

    @GetMapping("/rest")
    public ResponseEntity<Greeting> greet(@RequestParam(defaultValue = "World", required = false) String name) {
        return ResponseEntity.ok(greetingMap.get(name));
    }

    @PostMapping("/rest/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Greeting postGreet(@PathVariable String name) {
        /*
            Greeting greeting = greetingMap.get(name);
            if (greeting == null) {
                greeting = new Greeting(String.format("Hello, %s!", name));
                greetingMap.put(name, greeting);
            }
            return greeting;
        */
        return greetingMap.computeIfAbsent(name, newName -> new Greeting(String.format("Hello, %s!", newName)));
    }
}
