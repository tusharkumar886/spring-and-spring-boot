package com.oreilly.shopping.entities;

import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ProductTest {

    @Autowired
    private Validator validator;

    @Test
    void validProduct() {
        Product product = Product.builder().name("Watch").price(BigDecimal.valueOf(149.95)).build();
        var violations = validator.validate(product);
        assertTrue(violations.isEmpty());
    }

    @Test
    void invalidProduct() {
        Product product = Product.builder().name(" ").price(BigDecimal.valueOf(-149.95)).build();
        var violations = validator.validate(product);
        assertFalse(violations.isEmpty());
        violations.forEach(System.out::println);
    }
}