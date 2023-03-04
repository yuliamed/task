package by.iba.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
     String uploadFile(String folder, MultipartFile file);
     byte[] downloadFile(String fileName);
     void deleteFile(String fileName);
}
