package com.oreilly.shopping.repositories;

import com.oreilly.shopping.entities.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTCTest {

    @Container
    @ServiceConnection
    private static final PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:15-alpine");

    @Autowired
    private ProductRepository repository;

    @Test
    void testSave() {
        Product bat = Product.builder().name("cricket bat").price(BigDecimal.valueOf(35.00)).build();
        repository.saveAndFlush(bat);
        assertThat(repository.count()).isEqualTo(1L);
    }

    @Test
    void testRepository() {
        assertNotNull(postgreSQLContainer);
        System.out.println("Test container running on port: " + postgreSQLContainer.getFirstMappedPort());
        System.out.println("Test container running on host: " + postgreSQLContainer.getHost());
        System.out.println("Test container running on JDBC URL: " + postgreSQLContainer.getJdbcUrl());
        System.out.println("Test container running on username: " + postgreSQLContainer.getUsername());
        System.out.println("Test container running on password: " + postgreSQLContainer.getPassword());
        System.out.println("Test container running on database name: " + postgreSQLContainer.getDatabaseName());
        System.out.println("Test container running on database driver: " + postgreSQLContainer.getDriverClassName());
    }
}
