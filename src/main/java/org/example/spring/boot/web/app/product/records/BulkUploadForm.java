package org.example.spring.boot.web.app.product.records;

import org.springframework.web.multipart.MultipartFile;

public record BulkUploadForm(String fileName, MultipartFile file) {
}
