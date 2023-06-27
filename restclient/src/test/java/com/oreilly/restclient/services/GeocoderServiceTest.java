package com.oreilly.restclient.services;

import com.oreilly.restclient.json.GeocodeResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GeocoderServiceTest {

    @Autowired
    GeocoderService geocoderService;

    @Test
    void getLatLong() {
        GeocodeResponse response = geocoderService.getLatLong("Delhi");
        assertAll(
                () -> assertEquals("OK", response.status()),
                () -> assertEquals("Delhi, India", response.results().get(0).formattedAddress()),
                () -> assertEquals(28.70, response.results().get(0).geometry().location().latitude(), 0.01),
                () -> assertEquals(77.10, response.results().get(0).geometry().location().longitude(), 0.01)
        );
    }
}