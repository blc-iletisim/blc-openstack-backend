package com.blc.customerInterface;

import com.blc.customerInterface.graphql.image.domain.Image;
import com.blc.customerInterface.graphql.image.repo.ImageRepo;
import com.blc.customerInterface.graphql.image.service.ImageService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ImageMutationTest {

    @Autowired
    private ImageService imageService;

    @Autowired
    private ImageRepo imageRepo;

    @Test
    @Order(1)
   // @Rollback(value = false)
    public  void createImageTest(){
        Image image= Image.builder()
                .name("Deneme")
                .build();

        imageService.save(image);

        Assertions.assertThat(image.getId()).isGreaterThan(UUID.randomUUID());

    }

    @Test
    @Order(2)
    public void getImageTest(){

        Image image = imageService.findAll().get(0);

        Assertions.assertThat(image.getId()).isEqualTo(imageService.findAll().get(0).getId());

    }

    @Test
    @Order(3)
    public void getListOfImagesTest(){

        List<Image> images = imageService.findAll();

        Assertions.assertThat(images.size()).isGreaterThan(0);

    }

    @Test
    @Order(4)
   // @Rollback(value = false)
    public void updateImageTest(){

        Image image = imageService.findAll().get(0);

        image.setName("Deneme");

        Image imageUpdated =  imageService.save(image);

        Assertions.assertThat(imageUpdated.getName()).isEqualTo("Deneme");

    }

    @Test
    @Order(5)
   // @Rollback(value = false)
    public void deleteImageTest(){

        Image image = imageService.findAll().get(0);

        imageService.delete(image);

        Image image1 = null;

        Optional<Image> optionalImage = imageRepo.findByName("Deneme");

        if(optionalImage.isPresent()){
            image1= optionalImage.get();
        }

        Assertions.assertThat(image1).isNull();
    }
}
