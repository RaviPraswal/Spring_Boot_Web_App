package org.example.spring.boot.web.app.product.services;

import org.example.spring.boot.web.app.product.records.BulkUploadForm;
import org.example.spring.boot.web.app.product.models.Product;
import org.example.spring.boot.web.app.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    /*List<Product> products= new ArrayList(Arrays.asList(
            new Product(100,"HP Laptop",50000),
            new Product(101,"Dell Laptop",60000),
            new Product(102,"Apple Laptop",100000)
    ));*/

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

    public int processFile(BulkUploadForm form) {
        Objects.requireNonNull(form);







        return 1;
    }
}
