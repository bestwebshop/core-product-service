package tech.bestwebshop.api.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.bestwebshop.api.productservice.exception.ResourceNotFoundException;
import tech.bestwebshop.api.productservice.model.Product;
import tech.bestwebshop.api.productservice.repositories.ProductRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/products")
    public Iterable<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value="id") Integer productId) {
	System.out.println("#### Get Product by id " + productId);
        Optional<Product> optionalProduct = productRepository.findById(productId);
        return optionalProduct.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        //return productRepository.findById(productId)
        //        .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
    }

    @PostMapping("/products")
    public Product createProduct(@Valid @RequestBody Product product){
        return productRepository.save(product);
    }

}
