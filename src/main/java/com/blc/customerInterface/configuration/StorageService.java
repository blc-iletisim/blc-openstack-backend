package com.blc.customerInterface.configuration;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class StorageService {

    private final String uploadPath;

    public StorageService(@Value("${storage.upload-path}") String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public String save(MultipartFile file){
        try {
            Path target = Paths.get(uploadPath).resolve(String.format("%s.%s", UUID.randomUUID(), FilenameUtils.getExtension(file.getOriginalFilename())));
            //Files.copy(file.getInputStream(), target);
            return target.getFileName().toString();
        } catch (Exception e) {
           throw  new RuntimeException(e.getMessage(), e);
        }
    }

    public void delete(String file) {
        try {
            Files.delete(Paths.get(uploadPath).resolve(file));
        } catch (IOException e) {
            throw  new RuntimeException(e.getMessage(), e);
        }
    }
}
