package com.blc.customerInterface;

import com.blc.customerInterface.graphql.company.domain.Company;
import com.blc.customerInterface.graphql.company.repo.CompanyRepo;
import com.blc.customerInterface.graphql.company.service.CompanyService;
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
public class CompanyMutationTest {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyRepo companyRepo;

    @Test
    @Order(1)
    // @Rollback(value = false)
    public  void createCompanyTest(){
        Company company= Company.builder()
                .name("Deneme")
                .build();

        companyService.save(company);

        Assertions.assertThat(company.getId());

    }

    @Test
    @Order(2)
    public void getCompanyTest(){

        Company company = companyService.findAll().get(0);

        Assertions.assertThat(company.getId()).isEqualTo(companyService.findAll().get(0).getId());

    }

    @Test
    @Order(3)
    public void getListOfCompanysTest(){

        List<Company> companies = companyService.findAll();

        Assertions.assertThat(companies.size()).isGreaterThan(0);

    }

    @Test
    @Order(4)
    // @Rollback(value = false)
    public void updateCompanyTest(){

        Company company = companyService.findAll().get(0);

        company.setName("Deneme");

        Company companyUpdated =  companyService.save(company);

        Assertions.assertThat(companyUpdated.getName()).isEqualTo("Deneme");

    }

    @Test
    @Order(5)
    // @Rollback(value = false)
    public void deleteCompanyTest(){

        Company company = companyService.findAll().get(0);

        companyService.delete(company);

        Company company1 = null;

        Optional<Company> optionalCompany = Optional.ofNullable(companyRepo.findByName("Deneme"));

        if(optionalCompany.isPresent()){
            company1= optionalCompany.get();
        }

        Assertions.assertThat(company1).isNull();
    }
}
