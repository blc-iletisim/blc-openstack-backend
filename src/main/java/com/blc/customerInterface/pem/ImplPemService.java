package com.blc.customerInterface.pem;

import com.blc.customerInterface.configuration.DefaultResponse;

import com.blc.customerInterface.configuration.FileStorageProperties;
import com.blc.customerInterface.configuration.StorageService;
import com.blc.customerInterface.exception.FileStorageException;
import com.blc.customerInterface.exception.MyFileNotFoundException;
import com.blc.customerInterface.graphql.instance.service.InstanceService;
import com.blc.customerInterface.graphql.user.domain.User;
import com.blc.customerInterface.graphql.user.repo.UserRepo;
import com.blc.customerInterface.jwt.JwtTokenProvider;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import javax.transaction.Transactional;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static io.swagger.v3.oas.integration.StringOpenApiConfigurationLoader.LOGGER;


@Service
@Transactional
public class ImplPemService implements PemService{

   // protected final static Logger LOGGER = Logger.getLogger(ImplPemService.class);

    private static final int KEY_SIZE = 1024;


    @Autowired
    private UserRepo userRepository;
    @Autowired
    private InstanceService instanceService;
    @Autowired
    private PemRepository pemRepository;
    @Autowired
    private StorageService storageService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    @Value("${storage.upload-path}")
    private String uploadPath;


    @Override
    public DefaultResponse<Pem> createPem(String file_name, String token) throws IOException, NoSuchAlgorithmException, NoSuchProviderException {
        String[] token2= token.split(" ");
        token = token2[1];
        String userName = jwtTokenProvider.getUserNameFromJwt(token);
        User user = userRepository.findByEmail(userName).orElse(null);

        File file = new File(uploadPath,file_name+".pem");

        Security.addProvider(new BouncyCastleProvider());
        LOGGER.info("BouncyCastle provider added.");

        KeyPair keyPair = generateRSAKeyPair();
        RSAPrivateKey priv = (RSAPrivateKey) keyPair.getPrivate();
        writePemFile(priv, "RSA PRIVATE KEY", file);
        Pem pem = new Pem();
        pem.setName(file_name+".pem");
        pem.setPem_url(file.getAbsolutePath());
        pem.setUser(user);
        pemRepository.save(pem);
        return new DefaultResponse<>(true,"",pem);
    }

    @Override
    public DefaultResponse<Pem> uploadFile(MultipartFile file, String token) {
        try {
            String[] token2= token.split(" ");
            token = token2[1];
            String userName = jwtTokenProvider.getUserNameFromJwt(token);
            User user = userRepository.findByEmail(userName).orElse(null);
            String fileName = storeFile(file);
            String pemName = null;

            System.out.println("filename " +fileName);
            boolean status = false;

            List<Pem> userPems = new ArrayList<>();
            for (int i =0; i<pemRepository.findAll().size(); i++){
                if (pemRepository.findAll().get(i).getUser() == user){
                    Pem pem = pemRepository.findAll().get(i);
                    userPems.add(pem);
                }
            }



            for (int i =0; i<userPems.size(); i++){
                 pemName=userPems.get(i).getName();
                if (pemName.equals(fileName)){
                    status = true;
                    break;
                }else {
                    status = false;
                }

            }


            if (status ==true){
                for (int i =0; i<userPems.size(); i++){
                    Pem pem = userPems.get(i);
                    if (pem.getName().equals(pemName)){
                        return new DefaultResponse<>(true,"",pem);
                    }
                }
            return new DefaultResponse<>(false,null);
            }else {
                Pem pem = new Pem();
                pem.setUser(user);
                pem.setName(fileName);
                pem.setPem_url(uploadPath);
                System.out.println("pemmmmm 2222" + pem.getName());
                pemRepository.save(pem);
                return new DefaultResponse<>(true,"",pem);
            }

        }catch (Exception e){
            return new DefaultResponse<>(false,null);
        }
    }


    private static KeyPair generateRSAKeyPair() throws NoSuchAlgorithmException, NoSuchProviderException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");
        generator.initialize(KEY_SIZE);

        KeyPair keyPair = generator.generateKeyPair();
        LOGGER.info("RSA key pair generated.");
        return keyPair;
    }

    private static void writePemFile(Key key, String description, File file)
            throws FileNotFoundException, IOException {
        PemFile pemFile = new PemFile(key, description);
        pemFile.write(file);

        LOGGER.info(String.format("%s successfully writen in file %s.", description, file.getName()));
    }

    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // Check if the file's name contains invalid characters
        if(fileName.contains("..")) {
            throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
        }

        // Copy file to the target location (Replacing existing file with the same name)
        //  Path targetLocation = this.fileStorageLocation.resolve(fileName);
        //  Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        return fileName;
    }



}
