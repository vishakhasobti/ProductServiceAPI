package com.scalar.productservice.service;

import com.scalar.productservice.dto.FakeStoreProductDto;
import com.scalar.productservice.exceptions.ProductNotExistsException;
import com.scalar.productservice.model.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long id) throws ProductNotExistsException;

    List<Product> getAllProducts();

Product addnewProduct(Product product);

    Product replaceProduct(Long id, Product product);

    boolean deleteProduct(Long id);

    Product updateProduct(Long id, Product product);



}
