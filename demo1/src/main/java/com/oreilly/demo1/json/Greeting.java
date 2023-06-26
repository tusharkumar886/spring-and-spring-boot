package com.oreilly.demo1.json;

import java.util.Objects;

public record Greeting(String message) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Greeting gr)) return false;
        return Objects.equals(message, gr.message);
    }
}
