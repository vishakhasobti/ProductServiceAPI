package com.scalar.productservice.repositories;

import com.scalar.productservice.model.Category;
import com.scalar.productservice.model.Product;
import com.scalar.productservice.repositories.projections.ProductWithIdAndTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository
        extends JpaRepository<Product, Long> {
    Product findByid (Long id);
    List<Product> findByTitleContaining(String title);

    Product save(Product product);

    //    @Query("select p from Product p")
    List<Product> findAll();

}
