package org.example.spring.boot.web.app.product.records;

import org.springframework.web.multipart.MultipartFile;

public class BulkUploadForm {
    private String fileName;
    private MultipartFile file;  // Ensure this is included for file uploads

    // Getters and Setters
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
