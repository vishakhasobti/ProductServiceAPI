package com.scalar.productservice.service;

import com.scalar.productservice.dto.FakeStoreProductDto;
import com.scalar.productservice.exceptions.ProductNotExistsException;
import com.scalar.productservice.model.Category;
import com.scalar.productservice.model.Product;
import com.scalar.productservice.repositories.CategoryRepository;
import com.scalar.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
// prioritize this one @Primary

public class SelfProductService implements ProductService{

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    @Autowired
    public SelfProductService(ProductRepository productRepository , CategoryRepository categoryRepository){
    this.productRepository = productRepository;
    this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getSingleProduct(Long id) throws ProductNotExistsException {
       Optional<Product> productOptional = productRepository.findById(1L);
       if(productOptional.isEmpty()){

           throw new ProductNotExistsException("Product with id: " + id + " doesn't exist.");
       }

       Product product=productOptional.get();

       return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product addnewProduct(Product product) {
//way1 Save the category
//        Category category = product.getCategory();
//        if(category.getId() == null){
//            Category saveCategory = categoryRepository.save(category);
//            product.setCategory(saveCategory);
//        }
//
//        //another way
//        Optional<Category> categoryoptional = categoryRepository.findByName(product.getCategory().getName());
//
//        if (categoryoptional.isEmpty()) {
//        product.setCategory(categoryRepository.save(product.getCategory()));
//        } else {
//            product.setCategory(categoryoptional.get());
//        }
//
//        return productRepository.save(product);

        Optional<Category> categoryOptional = Optional.ofNullable(categoryRepository.findByName(product.getCategory().getName()));

        if (categoryOptional.isEmpty()) {

        } else {
            product.setCategory(categoryOptional.get());
        }

        return productRepository.save(product);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public boolean deleteProduct(Long id) {
        return false;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        //update the particular fields. original product

        Optional<Product> productOptional = productRepository.findById(id);

        if(productOptional.isEmpty())
             throw new RuntimeException();

        Product savedproduct = productOptional.get();
        //check fields 1 by 1
        if(product.getTitle() != null){
            //update it
            savedproduct.setTitle(product.getTitle());
        }

        if(product.getDescription() != null){
            savedproduct.setDescription(product.getDescription());
        }

        if(product.getPrice() != null){
            savedproduct.setPrice(product.getPrice());
        }
        if(product.getImageUrl() != null){
            savedproduct.setImageUrl(product.getImageUrl());
        }



        return productRepository.save(savedproduct) ;
    }
}
