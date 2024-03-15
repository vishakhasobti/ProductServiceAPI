package com.scalar.productservice.controller;

import com.scalar.productservice.dto.FakeStoreProductDto;
import com.scalar.productservice.exceptions.ProductNotExistsException;
import com.scalar.productservice.model.Category;
import com.scalar.productservice.model.Product;
import com.scalar.productservice.repositories.CategoryRepository;
import com.scalar.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
//this class serves the API..

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private RestTemplate restTemplate;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductController(@Qualifier("selfProductService") ProductService productService, RestTemplate restTemplate,
                             CategoryRepository categoryRepository) {
        this.productService = productService;
        this.restTemplate = restTemplate;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/{id}")
    public Product getSingleProduct(@PathVariable("id") Long id) throws ProductNotExistsException {

        return productService.getSingleProduct(id);
    }

    @GetMapping // localhost:8080/products
    //type generics
    public ResponseEntity<List<Product>> getAllProduct() {
        ResponseEntity<List<Product>> response = new ResponseEntity<>(
                productService.getAllProducts(), HttpStatus.FORBIDDEN
        );
        return response;
    }

        @PostMapping
        public Product addnewProduct(@RequestBody Product product){

        return productService.addnewProduct(product);

        }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {

        return productService.updateProduct(id, product);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return new Product();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
