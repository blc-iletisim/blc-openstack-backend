package com.blc.customerInterface;

import com.blc.customerInterface.graphql.category.domain.Category;
import com.blc.customerInterface.graphql.role.domain.Role;
import com.blc.customerInterface.graphql.role.repo.RoleRepo;
import com.blc.customerInterface.graphql.role.service.RoleService;
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
public class RoleMutationTest {
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleRepo roleRepo;

    @Test
    @Order(1)
    // @Rollback(value = false)
    public  void createRoleTest(){
        Role role= Role.builder()
                .name("Deneme")
                .build();

        roleService.save(role);

        Assertions.assertThat(role.getId()).isGreaterThan(UUID.randomUUID());

    }

    @Test
    @Order(2)
    public void getRoleTest(){

        Role role = roleService.findAll().get(0);

        Assertions.assertThat(role.getId()).isEqualTo(roleService.findAll().get(0).getId());

    }
    @Test
    @Order(3)
    public void getListOfRolesTest(){

        List<Role> roles = roleService.findAll();

        Assertions.assertThat(roles.size()).isGreaterThan(0);

    }

    @Test
    @Order(4)
    // @Rollback(value = false)
    public void updateRoleTest(){

        Role role = roleService.findAll().get(0);

        role.setName("Deneme");

        Role roleUpdated =  roleService.save(role);

        Assertions.assertThat(roleUpdated.getName()).isEqualTo("Deneme");

    }
    @Test
    @Order(5)
    // @Rollback(value = false)
    public void deleteRoleTest(){

        Role role = roleService.findAll().get(0);

        roleService.delete(role);

        Role role1 = null;

        Optional<Role> optionalRole = Optional.ofNullable(roleRepo.findByName("Deneme"));

        if(optionalRole.isPresent()){
            role1= optionalRole.get();
        }

        Assertions.assertThat(role1).isNull();
    }
}
