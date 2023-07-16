package com.oreilly.shopping.services;

import com.oreilly.shopping.entities.Product;
import com.oreilly.shopping.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void initializeDataBase() {
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
    }

    @Transactional(readOnly = true)
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Product> findAllByPriceGreaterThanEqual(BigDecimal minPrice) {
        return productRepository.findAllByPriceGreaterThanEqual(minPrice);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }
}
