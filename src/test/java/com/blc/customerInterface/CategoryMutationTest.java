package com.blc.customerInterface;

import com.blc.customerInterface.graphql.category.domain.Category;
import com.blc.customerInterface.graphql.category.repo.CategoryRepo;
import com.blc.customerInterface.graphql.category.service.CategoryService;
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
import java.util.UUID;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryMutationTest {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepo categoryRepo;

    @Test
    @Order(1)
    // @Rollback(value = false)
    public  void createCategoryTest(){
        Category category= Category.builder()
                .name("Deneme")
                .build();

        categoryService.save(category);

        Assertions.assertThat(category.getId());

    }

    @Test
    @Order(2)
    public void getCategoryTest(){

        Category category = categoryService.findAll().get(0);

        Assertions.assertThat(category.getId()).isEqualTo(categoryService.findAll().get(0).getId());

    }

    @Test
    @Order(3)
    public void getListOfCategoriesTest(){

        List<Category> categories = categoryService.findAll();

        Assertions.assertThat(categories.size()).isGreaterThan(0);

    }

    @Test
    @Order(4)
    // @Rollback(value = false)
    public void updateCategoryTest(){

        Category category = categoryService.findAll().get(0);

        category.setName("Deneme");

        Category categoryUpdated =  categoryService.save(category);

        Assertions.assertThat(categoryUpdated.getName()).isEqualTo("Deneme");

    }

    @Test
    @Order(5)
    // @Rollback(value = false)
    public void deleteCategoryTest(){

        Category category = categoryService.findAll().get(0);

        categoryService.delete(category);

        Category category1 = null;

        Optional<Category> optionalCategory = categoryRepo.findByName("Deneme");

        if(optionalCategory.isPresent()){
             category1= optionalCategory.get();
        }

        Assertions.assertThat(category1).isNull();
    }
}
