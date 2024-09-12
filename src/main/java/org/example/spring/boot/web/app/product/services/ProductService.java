package org.example.spring.boot.web.app.product.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.spring.boot.web.app.product.records.BulkUploadForm;
import org.example.spring.boot.web.app.product.models.Product;
import org.example.spring.boot.web.app.product.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    @Autowired
    ProductRepository productRepository;

    private final ObjectMapper objectMapper;

    /*List<Product> products= new ArrayList(Arrays.asList(
            new Product(100,"HP Laptop",50000),
            new Product(101,"Dell Laptop",60000),
            new Product(102,"Apple Laptop",100000)
    ));*/

    public ProductService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(long id) {
        /*return products.stream().filter(product->product.getId()==id).findFirst().orElse(new Product(id,"No Product",0));*/
        return productRepository.findById(id).get();

    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public void updateProduct(Product product) {
//        for(int i=0; i<products.size(); i++){
//            if(products.get(i).getId()==product.getId()){
//                products.set(i,product);
//                break;
//            }
//        }
        productRepository.save(product);

    }

    public void deleteProductById(long productId) {
        /*Product product = getProductById(productId);
        products.remove(product);*/
        productRepository.deleteById(productId);
    }

    public Page<Product> getProductsByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable);
    }

    public List<Product> getProductsByKeyword(String keyword) {
        return productRepository.getProductsByKeyword(keyword);
    }

    public Page<Product> findByNameContainingOrDescriptionContaining(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findByNameContainingOrDescriptionContaining(keyword, keyword, pageable);

    }

    public int bulkUpload(BulkUploadForm form) {
        try {
            var data =new String(form.getFile().getBytes());
            Product[] productsArr = objectMapper.readValue(data, Product[].class);
            List<Product> batch = Arrays.asList(productsArr);
            productRepository.saveAll(batch);

        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return 1;
    }
}
