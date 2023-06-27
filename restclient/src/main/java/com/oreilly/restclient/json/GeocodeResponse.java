package com.oreilly.restclient.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record GeocodeResponse(List<Result> results, String status) {
    public static record Result(String formattedAddress, Geometry geometry) {
        public static record Geometry(Location location) {
            public static record Location(@JsonProperty("lat") double latitude, @JsonProperty("lng") double longitude) {
            }
        }
    }
}
