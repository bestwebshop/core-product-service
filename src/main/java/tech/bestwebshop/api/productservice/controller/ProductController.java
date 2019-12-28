package tech.bestwebshop.api.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.bestwebshop.api.productservice.exception.ResourceNotFoundException;
import tech.bestwebshop.api.productservice.model.Product;
import tech.bestwebshop.api.productservice.repositories.ProductRepository;

import javax.validation.Valid;
import java.util.List;


@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/product")
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable(value="id") Integer productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
    }

    @PostMapping("/product")
    public Product createProduct(@Valid @RequestBody Product product){
        return productRepository.save(product);
    }

}
