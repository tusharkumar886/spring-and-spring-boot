package com.oreilly.shopping.config;

import com.oreilly.shopping.entities.Product;
import com.oreilly.shopping.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    public CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
            if (productRepository.count() == 0) {
                productRepository.saveAll(
                        List.of(
                                Product.builder().name("Watch").price(BigDecimal.valueOf(149.95)).build(),
                                Product.builder().name("TV").price(BigDecimal.valueOf(499.0)).build(),
                                Product.builder().name("Sofa").price(BigDecimal.valueOf(399.0)).build(),
                                Product.builder().name("Microwave").price(BigDecimal.valueOf(299.0)).build()
                        )
                ).forEach(System.out::println);
            }
        };
    }
}
