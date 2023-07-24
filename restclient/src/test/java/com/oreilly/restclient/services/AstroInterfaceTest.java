package com.oreilly.restclient.services;

import com.oreilly.restclient.json.AstroResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AstroInterfaceTest {

    @Autowired
    private AstroInterface astroInterface;

    @Test
    void getAstroResponse() {
        AstroResponse response = astroInterface.getAstroResponse();
        assertAll(
                () -> assertNotNull(response),
                () -> assertThat(response.number().intValue()).isPositive(),
                () -> assertSame(response.number(), response.people().size()),
                () -> assertEquals("success", response.message())
        );
        System.out.println(response);
    }

}