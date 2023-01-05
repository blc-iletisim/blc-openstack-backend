package com.blc.customerInterface;

import com.blc.customerInterface.graphql.category.domain.Category;
import com.blc.customerInterface.graphql.category.service.CategoryService;
import com.blc.customerInterface.graphql.flavor.domain.Flavor;
import com.blc.customerInterface.graphql.flavor.service.FlavorService;
import com.blc.customerInterface.graphql.image.domain.Image;
import com.blc.customerInterface.graphql.image.service.ImageService;
import com.blc.customerInterface.graphql.instance.domain.Instance;
import com.blc.customerInterface.graphql.instance.repo.InstanceRepo;
import com.blc.customerInterface.graphql.instance.service.InstanceService;
import com.blc.customerInterface.graphql.user.domain.User;
import com.blc.customerInterface.graphql.user.service.UserService;
import com.blc.customerInterface.pem.Pem;
import com.blc.customerInterface.pem.PemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class InstanceMutationTest {
    @Autowired
    private InstanceService instanceService;
    @Autowired
    private InstanceRepo instanceRepo;
    @Autowired
    private FlavorService flavorService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

    @Autowired
    private PemRepository pemRepository;


    @Test
    @Order(1)
    @Rollback(value = false)
    public void createInstanceTest(){
        Image image = imageService.findAll().get(0);
        Flavor flavor = flavorService.findAll().get(0);
        Set<Category> categories = new HashSet<>();
        Category category = categoryService.findAll().get(0);
        categories.add(category);
        User user = userService.findAll().get(0);
        Pem pem = pemRepository.findAll().get(0);
        Instance instance= Instance.builder()
                .name("Deneme")
                .image(image)
                .flavor(flavor)
                .categories(categories)
                .pem(pem)
                .pemName(pem.getName())
                .user(user)
                .build();
        instanceService.save(instance);
        Assertions.assertThat(instance.getId()).isGreaterThan(UUID.randomUUID());
    }

    @Test
    @Order(2)
    public void getInstanceTest(){

        Instance instance = instanceService.findAll().get(0);

        Assertions.assertThat(instance.getId()).isEqualTo(instanceService.findAll().get(0).getId());

    }

    @Test
    @Order(3)
    public void getListOfInstancesTest(){

        List<Instance> instances = instanceService.findAll();

        Assertions.assertThat(instances.size()).isGreaterThan(0);

    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateInstanceTest(){
        Flavor flavor = flavorService.findAll().get(1);
        Set<Category> categories = new HashSet<>();
        Category category = categoryService.findAll().get(1);
        categories.add(category);

        Instance instance = instanceService.findAll().get(0);

        instance.setName("Deneme");
        instance.setFlavor(flavor);
        instance.setCategories(categories);

        Instance instanceUpdated =  instanceService.save(instance);

        Assertions.assertThat(instanceUpdated.getName()).isEqualTo("Deneme");

    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteInstanceTest(){

        Instance instance = instanceService.findAll().get(0);

        instanceService.delete(instance);

        Instance instance1 = null;

        Optional<Instance> optionalInstance = instanceRepo.findByName("Deneme");

        if(optionalInstance.isPresent()){
            instance1= optionalInstance.get();
        }

        Assertions.assertThat(instance1).isNull();
    }


}
