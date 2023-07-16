package com.oreilly.shopping.controllers;

import com.oreilly.shopping.entities.Product;
import com.oreilly.shopping.exceptions.ProductMinimumPriceException;
import com.oreilly.shopping.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductRestController {

    private final ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @CrossOrigin
    public ResponseEntity<List<Product>> getProducts() {
//        return new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProduct(@PathVariable(name = "id") Long id) {
        return ResponseEntity.of(productService.findProductById(id));
    }

    @GetMapping(params = "minPrice")
    public ResponseEntity<List<Product>> getProductByMinPrice(@RequestParam(defaultValue = "0.0") double minPrice) {
        if(minPrice <0)
            throw new ProductMinimumPriceException(minPrice);
        return ResponseEntity.ok(productService.findAllByPriceGreaterThanEqual(minPrice));
    }

    @PostMapping
    public ResponseEntity<URI> addProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(savedProduct.getId())
                .toUri();
        return ResponseEntity.created(location).body(location);
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,
                                                 @Valid @RequestBody Product product) {
        return productService.findProductById(id)
                .map(p -> {
                    p.setName(product.getName());
                    p.setPrice(product.getPrice());
                    return ResponseEntity.ok(productService.saveProduct(p));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        return productService.findProductById(id)
                .map(p -> {
                    productService.deleteProduct(p);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
