package org.example.spring.boot.web.app.product.repository;

import org.example.spring.boot.web.app.product.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%',:keyword,'%')) ")
    List<Product> getProductsByKeyword(String keyword);

    Page<Product> findByNameContainingOrDescriptionContaining(String name, String description, Pageable pageable);


}
