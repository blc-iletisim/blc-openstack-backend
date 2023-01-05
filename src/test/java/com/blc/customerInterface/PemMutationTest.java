package com.blc.customerInterface;

import com.blc.customerInterface.graphql.user.domain.User;
import com.blc.customerInterface.graphql.user.service.UserService;
import com.blc.customerInterface.pem.Pem;
import com.blc.customerInterface.pem.PemRepository;
import org.assertj.core.api.Assertions;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.io.File;
import java.io.IOException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.util.List;
import java.util.UUID;

import static com.blc.customerInterface.pem.ImplPemService.generateRSAKeyPair;
import static com.blc.customerInterface.pem.ImplPemService.writePemFile;
import static io.swagger.v3.oas.integration.StringOpenApiConfigurationLoader.LOGGER;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PemMutationTest {
    @Autowired
    private PemRepository pemRepository;
    @Autowired
    @Value("${storage.upload-path}")
    private String uploadPath;

    @Autowired
    private UserService userService;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void createPemTest() throws NoSuchAlgorithmException, NoSuchProviderException, IOException {
        User user = userService.findAll().get(0);

        File file = new File(uploadPath,"deneme2.pem");

        Security.addProvider(new BouncyCastleProvider());
        LOGGER.info("BouncyCastle provider added.");

        KeyPair keyPair = generateRSAKeyPair();
        RSAPrivateKey priv = (RSAPrivateKey) keyPair.getPrivate();
        writePemFile(priv, "RSA PRIVATE KEY", file);
        Pem pem = Pem.builder()
                .name("deneme2")
                .pem_url(file.getAbsolutePath())
                .user(user)
                .build();
        pemRepository.save(pem);

        Assertions.assertThat(pem.getId()).isGreaterThan(UUID.randomUUID());
    }

    @Test
    @Order(2)
    public void getPemsToUserTest(){
        User user = userService.findAll().get(0);
        List<Pem> pems = user.getPems();
        Assertions.assertThat(pems.size()).isGreaterThan(0);
    }
}
