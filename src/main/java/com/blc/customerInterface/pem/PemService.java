package com.blc.customerInterface.pem;

import com.blc.customerInterface.configuration.DefaultResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.UUID;

public interface PemService {
    DefaultResponse<Pem> createPem(String file_name, String token) throws IOException, NoSuchAlgorithmException, NoSuchProviderException;
}
