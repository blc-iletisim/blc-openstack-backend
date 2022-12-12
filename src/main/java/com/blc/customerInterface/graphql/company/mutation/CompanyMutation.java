package com.blc.customerInterface.graphql.company.mutation;

import com.blc.customerInterface.graphql.company.domain.Company;
import com.blc.customerInterface.graphql.company.mutation.input.CompanyCreateInput;
import com.blc.customerInterface.graphql.company.mutation.input.CompanyUpdateInput;
import com.blc.customerInterface.graphql.company.mutation.mapper.CompanyMapper;
import com.blc.customerInterface.graphql.company.service.CompanyService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.UUID;

@Component
@Validated
public class CompanyMutation  implements GraphQLMutationResolver {

    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    @Autowired
    public CompanyMutation(CompanyService companyService, CompanyMapper companyMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
    }

    public Company createCompany(@Valid CompanyCreateInput input){
        return companyService.save(companyMapper.toEntity(input));
    }

    public Company updateCompany(UUID id, @Valid CompanyUpdateInput input){
        return companyService.findById(id).map(company ->companyService
                .update(companyMapper.updateEntity(company,input))).orElseThrow(RuntimeException::new);
    }
    public UUID deleteCompany(UUID id){
        return companyService.findById(id).map(companyService::delete).orElseThrow(RuntimeException::new);
    }
    public Company undeleteCompany(UUID id){
        return companyService.findById(id).map(companyService::undelete).orElseThrow(RuntimeException::new);
    }


}
