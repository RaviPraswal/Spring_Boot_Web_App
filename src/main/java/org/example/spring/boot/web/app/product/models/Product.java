package org.example.spring.boot.web.app.product.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Double price;
    private String description;
    private String category;
    private String manufacturer;
    private Double rating;
    private Integer stock;
    private String imageUrl;
    private LocalDate releaseDate;
    private String warranty;
    private String dimensions;
    private Double weight;

}
