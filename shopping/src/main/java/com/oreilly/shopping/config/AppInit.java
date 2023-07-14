package com.oreilly.shopping.config;

import com.oreilly.shopping.entities.Product;
import com.oreilly.shopping.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class AppInit implements CommandLineRunner {

    private final ProductRepository productRepository;

    // if only one dependency, spring will automatically inject that dependency without adding @Autowired
    public AppInit(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
/*
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
*/
    }
}
