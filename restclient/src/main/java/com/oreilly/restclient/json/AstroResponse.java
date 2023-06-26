package com.oreilly.restclient.json;

import java.util.List;

public record AstroResponse(Number number, List<Assignment> people, String message) {

    @Override
    public String toString() {
        return "AstroResponse{" +
                "message='" + message + '\'' +
                ", number=" + number +
                ", people=" + people +
                '}';
    }
}
