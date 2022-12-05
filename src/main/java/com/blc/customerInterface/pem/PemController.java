package com.blc.customerInterface.pem;


import com.blc.customerInterface.configuration.DefaultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;
import java.util.UUID;

@Component
@RequestMapping(path= "/pem")
public class PemController {

    private final PemService pemService;

    @Autowired
    public PemController(PemService pemService) {
        this.pemService = pemService;
    }

    @PostMapping("/createPem")
    public ResponseEntity<Pem> createPem(@RequestParam("file_name") String file_name, @RequestHeader("Authorization") String token) throws IOException, NoSuchProviderException, NoSuchAlgorithmException {

        DefaultResponse<Pem> pem = pemService.createPem(file_name,token);
        if (pem.isSuccess()){
            return new ResponseEntity<>(pem.getData(), HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PostMapping("/uploadFile")
    public  ResponseEntity<Pem> uploadFile(@RequestParam(value =  "file") MultipartFile file,
                                           @RequestHeader("Authorization") String token){
        DefaultResponse<Pem> pem = pemService.uploadFile(file,token);
        if (pem.isSuccess()){
            return new ResponseEntity<>(pem.getData(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/getPemsToUser")
    public ResponseEntity<List<Pem>> getPemsToUser(@RequestHeader("Authorization") String token){
        DefaultResponse<List<Pem>> pems = pemService.getPemsToUser(token);
        if (pems.isSuccess()){
            return new ResponseEntity<>(pems.getData(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }

}
