package com.scalar.productservice;

import com.scalar.productservice.model.Category;
import com.scalar.productservice.repositories.CategoryRepository;
import com.scalar.productservice.repositories.ProductRepository;
import com.scalar.productservice.repositories.projections.ProductWithIdAndTitle;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.List;
import java.util.Optional;

//@SpringBootTest
class ProductServiceApplicationTests {
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Autowired
//    private CategoryRepository categoryRepository;
//
////    public ProductservicedecmwfeveApplication(ProductRepository productRepository) {
////        this.productRepository = productRepository;
////    }
//
//    @Test
//    void contextLoads() {
//    }
//
//    @Test
//    @Transactional
//    @Commit
//    void testQueries() {
//        productRepository.findByTitleContaining("naman");
////        productRepository.deleteByTitle("naman");
//
////        List<ProductWithIdAndTitle> products = productRepository.somethingsomething(102L);
//
//        for (ProductWithIdAndTitle product: products) {
//            System.out.println(product.getId());
//            System.out.println(product.getTitle());
//        }
////
////        List<ProductWithIdAndTitle> products1 = productRepository.somesome2(102L);
//
//        Optional<Category> category = categoryRepository.findById(52L);
//
//    }


}
