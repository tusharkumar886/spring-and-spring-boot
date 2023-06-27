package com.oreilly.restclient.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record GeocodeResponse(List<Result> results, String status) {
    public record Result(@JsonProperty("formatted_address") String formattedAddress, Geometry geometry) {
        public record Geometry(Location location) {
            public record Location(@JsonProperty("lat") double latitude, @JsonProperty("lng") double longitude) {
            }
        }
    }
}
