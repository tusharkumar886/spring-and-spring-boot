package com.oreilly.restclient.services;

import com.oreilly.restclient.json.AstroResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AstroServiceTest {

    @Autowired
    private AstroService astroService;

    @Test
    void getAstronauts() {
        String jsonString = astroService.getAstronauts();
        assertTrue(jsonString.contains("people"));
        System.out.println(jsonString);
    }

    @Test
    void getAstroResponse() {
        AstroResponse response = astroService.getAstroResponse();
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.number().intValue() >= 0),
                () -> assertSame(response.number(), response.people().size()),
                () -> assertEquals("success", response.message())
        );
        System.out.println(response);
    }

    @Test
    void getAstroResponseAdv() {
        AstroResponse response = astroService.getAstroResponseAdv();
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.number().intValue() >= 0),
                () -> assertSame(response.number(), response.people().size()),
                () -> assertEquals("success", response.message())
        );
        System.out.println(response);
    }

    @Test
    void getAstroResponseRestTemplate() {
        AstroResponse response = astroService.getAstroResponseRestTemplate();
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.number().intValue() >= 0),
                () -> assertSame(response.number(), response.people().size()),
                () -> assertEquals("success", response.message())
        );
        System.out.println(response);
    }

}