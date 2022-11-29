package com.blc.customerInterface.pem;

import com.blc.customerInterface.configuration.DefaultResponse;

import com.blc.customerInterface.configuration.StorageService;
import com.blc.customerInterface.graphql.instance.service.InstanceService;
import com.blc.customerInterface.graphql.user.domain.User;
import com.blc.customerInterface.graphql.user.repo.UserRepo;
import com.blc.customerInterface.jwt.JwtTokenProvider;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.io.*;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;

import static io.swagger.v3.oas.integration.StringOpenApiConfigurationLoader.LOGGER;


@Service
@Transactional
public class ImplPemService implements PemService{

   // protected final static Logger LOGGER = Logger.getLogger(ImplPemService.class);

    public static final int KEY_SIZE = 1024;

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
        pem.setName(file_name);
        pem.setPem_url(file.getAbsolutePath());
        pem.setUser(user);
        pemRepository.save(pem);
        return new DefaultResponse<>(true,"",pem);
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

}
