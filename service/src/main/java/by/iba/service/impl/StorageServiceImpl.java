package by.iba.service.impl;

import by.iba.exception.ServiceException;
import by.iba.service.StorageService;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@AllArgsConstructor
@Service
public class StorageServiceImpl implements StorageService {
    //    @Value("${application.bucket.name")
//    private String bucketName;
    private final AmazonS3 s3Client;

    public String uploadFile(String folder, MultipartFile file) {
        String fileName = folder + "/" + System.currentTimeMillis() + "_" + file.getOriginalFilename();
        s3Client.putObject(new PutObjectRequest("yulinusersphotos", fileName, convertMultipartFileToFile(file)));
        return fileName;
    }

    public byte[] downloadFile(String fileName) {
        S3Object s3Object = s3Client.getObject("yulinusersphotos", fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void deleteFile(String fileName) {

        s3Client.deleteObject("yulinusersphotos", fileName);
    }

    private File convertMultipartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());

        } catch (IOException e) {
            throw new ServiceException("Error in converting file");
        }
        return convertedFile;
    }
}
