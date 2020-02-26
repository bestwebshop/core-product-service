package tech.bestwebshop.api.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.bestwebshop.api.productservice.model.Product;
import tech.bestwebshop.api.productservice.model.ProductDTO;
import tech.bestwebshop.api.productservice.repositories.ProductRepository;

import javax.annotation.security.RolesAllowed;
import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/products")
    @RolesAllowed({"USER"})
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    @RolesAllowed({"USER"})
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Integer productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        return optionalProduct.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/products")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductDTO productDTO) {
        try {
            Product product = productRepository.save(new Product(0, productDTO.getName(), productDTO.getPrice(),
                    productDTO.getCategoryID(), productDTO.getDetails()));
            return ResponseEntity.status(HttpStatus.CREATED).body(product);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/products/{id}")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") Integer productId,
                                                 @RequestBody @Valid Product productToUpdate){
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isPresent()){
            if(!optionalProduct.get().equals(productToUpdate)){
                try{
                    Product product = productRepository.save(productToUpdate);
                    return ResponseEntity.status(HttpStatus.ACCEPTED).body(product);
                } catch (Exception e){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
            } else {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(optionalProduct.get());
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/products/{id}")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<Product> deleteProduct(@PathVariable(value = "id") Integer productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()){
            productRepository.deleteById(productId);
            return ResponseEntity.accepted().body(optionalProduct.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
