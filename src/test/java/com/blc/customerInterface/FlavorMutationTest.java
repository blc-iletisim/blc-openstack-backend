package com.blc.customerInterface;

import com.blc.customerInterface.graphql.flavor.domain.Flavor;
import com.blc.customerInterface.graphql.flavor.repo.FlavorRepo;
import com.blc.customerInterface.graphql.flavor.service.FlavorService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;


@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FlavorMutationTest {

    @Autowired
    private FlavorService flavorService;
    @Autowired
    private FlavorRepo flavorRepo;

    @Test
    @Order(1)
    // @Rollback(value = false)
    public  void createFlavorTest(){
        Flavor flavor= Flavor.builder()
                .name("Deneme")
                .build();

        flavorService.save(flavor);

        Assertions.assertThat(flavor.getId());

    }

    @Test
    @Order(2)
    public void getFlavorTest(){

        Flavor flavor = flavorService.findAll().get(0);

        Assertions.assertThat(flavor.getId()).isEqualTo(flavorService.findAll().get(0).getId());

    }

    @Test
    @Order(3)
    public void getListOfCategoriesTest(){

        List<Flavor> flavors = flavorService.findAll();

        Assertions.assertThat(flavors.size()).isGreaterThan(0);

    }

    @Test
    @Order(4)
    // @Rollback(value = false)
    public void updateFlavorTest(){

        Flavor flavor = flavorService.findAll().get(0);

        flavor.setName("Deneme");

        Flavor flavorUpdated =  flavorService.save(flavor);

        Assertions.assertThat(flavorUpdated.getName()).isEqualTo("Deneme");

    }

    @Test
    @Order(5)
    // @Rollback(value = false)
    public void deleteFlavorTest(){

        Flavor flavor = flavorService.findAll().get(0);

        flavorService.delete(flavor);

        Flavor flavor1 = null;

        Optional<Flavor> optionalFlavor = flavorRepo.findByName("Deneme");

        if(optionalFlavor.isPresent()){
            flavor1= optionalFlavor.get();
        }

        Assertions.assertThat(flavor1).isNull();
    }
}
