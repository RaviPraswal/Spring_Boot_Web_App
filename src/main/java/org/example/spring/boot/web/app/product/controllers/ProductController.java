package org.example.spring.boot.web.app.product.controllers;

import jakarta.websocket.server.PathParam;
import org.example.spring.boot.web.app.product.exceptions.GlobalExceptionHandler;
import org.example.spring.boot.web.app.product.records.BulkUploadForm;
import org.example.spring.boot.web.app.product.models.Product;
import org.example.spring.boot.web.app.product.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @Autowired
    private ProductService productService;

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("/productss")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/products")
    public ResponseEntity<Page<Product>> getProductsByPage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        return new ResponseEntity<>(productService.getProductsByPage(page, size), HttpStatus.OK);
    }

    @GetMapping("/products/{productId}")
    public Product getProductById(@PathVariable Long productId){
        return productService.getProductById(productId);
    }

    @PostMapping("/add/product")
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @PutMapping("/update/product/{productId}")
    public void updateProduct(@PathVariable int productId, @RequestBody Product product){
        productService.updateProduct(product);
    }

    @DeleteMapping("/delete/product/{productId}")
    public void deleteProductById(@PathVariable Long productId){
        productService.deleteProductById(productId);
    }

//    @GetMapping("/search/products")
//    public ResponseEntity<List<Product>> getProductsByKeyword(@RequestParam String keyword){
//        List<Product> filteredProducts= productService.getProductsByKeyword(keyword);
//        if(filteredProducts!=null && !filteredProducts.isEmpty()){
//            return new ResponseEntity<>(filteredProducts, HttpStatus.OK);
//        }else{
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @GetMapping("/search/products")
    public ResponseEntity<Page<Product>> getProductsByKeywordAndPage(@RequestParam String keyword, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        log.info(keyword, page, size);
        return new ResponseEntity<>(productService.findByNameContainingOrDescriptionContaining(keyword, page, size), HttpStatus.OK);
    }

    @PostMapping("/upload/file")
    public ResponseEntity<String> uploadFile(@ModelAttribute BulkUploadForm form){
        try {
            System.out.println(form.getFile().getBytes().length);
            productService.bulkUpload(form);
            return ResponseEntity.status(HttpStatus.OK).body("File Uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while uploading the file. Please try again.");
        }
    }

}
