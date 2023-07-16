package com.oreilly.shopping.repositories;

import com.oreilly.shopping.entities.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProductRepositoryTest {
    @Autowired
    ProductRepository repository;

    @Test
    void countProducts() {
        assertEquals(4, repository.count());
    }

    @Test
    void findById() {
        assertTrue(repository.findById(1L).isPresent());
    }

    @Test
    void findAll() {
        List<Product> products = repository.findAll();
        assertEquals(4, products.size());
    }

    @Test
    void insertProduct() {
        Product bat = Product.builder().name("cricket bat").price(BigDecimal.valueOf(35.00)).build();
        repository.save(bat);
        assertAll(
                () -> assertNotNull(bat.getId()),
                () -> assertEquals(5, repository.count())
        );
    }

    @Test
    void deleteProduct() {
        repository.deleteById(1L);
        assertEquals(3, repository.count());
    }

    @Test
    void deleteAllInBatch() {
        repository.deleteAllInBatch();
        assertEquals(0, repository.count());
    }

    @Test
    void deleteAllProducts() {
        repository.deleteAll();
        assertEquals(0, repository.count());
    }
}