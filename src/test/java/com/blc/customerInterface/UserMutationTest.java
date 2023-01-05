package com.blc.customerInterface;

import com.blc.customerInterface.graphql.company.domain.Company;
import com.blc.customerInterface.graphql.company.service.CompanyService;
import com.blc.customerInterface.graphql.role.domain.Role;
import com.blc.customerInterface.graphql.role.service.RoleService;
import com.blc.customerInterface.graphql.user.domain.User;
import com.blc.customerInterface.graphql.user.repo.UserRepo;
import com.blc.customerInterface.graphql.user.service.UserService;
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
public class UserMutationTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private RoleService roleService;

    @Test
    @Order(1)
    // @Rollback(value = false)
    public  void createUserTest(){
        Company company = companyService.findAll().get(0);
        Role role = roleService.findAll().get(0);

        User user= User.builder()
                .name("Deneme")
                .email("deneme@blc.com")
                .password("deneme")
                .company(company)
                .role(role)
                .build();

        userService.save(user);

        Assertions.assertThat(user.getId()).isGreaterThan(UUID.randomUUID());

    }

    @Test
    @Order(2)
    public void getUserTest(){

        User user = userService.findAll().get(0);

        Assertions.assertThat(user.getId()).isEqualTo(userService.findAll().get(0).getId());

    }
    @Test
    @Order(3)
    public void getListOfUsersTest(){

        List<User> users = userService.findAll();

        Assertions.assertThat(users.size()).isGreaterThan(0);

    }

    @Test
    @Order(4)
    // @Rollback(value = false)
    public void updateUserTest(){

        Company company = companyService.findAll().get(0);

        Role role = roleService.findAll().get(0);

        User user = userService.findAll().get(0);

        user.setName("Deneme 2");
        user.setEmail("deneme2@blc.com");
        user.setPassword("deneme2");
        user.setCompany(company);
        user.setRole(role);

        User userUpdated =  userService.save(user);

        Assertions.assertThat(userUpdated.getName()).isEqualTo("Deneme 2");

    }


    @Test
    @Order(5)
    // @Rollback(value = false)
    public void deleteUserTest(){

        User user = userService.findAll().get(0);

        userService.delete(user);

        User user1 = null;

        Optional<User> optionalUser = userRepo.findByEmail("deneme@blc.com");

        if(optionalUser.isPresent()){
            user1= optionalUser.get();
        }

        Assertions.assertThat(user1).isNull();
    }
}
