package com.blc.customerInterface.graphql.company.mutation;

import com.blc.customerInterface.graphql.company.domain.Company;
import com.blc.customerInterface.graphql.company.mutation.input.CompanyCreateInput;
import com.blc.customerInterface.graphql.company.mutation.input.CompanyUpdateInput;
import com.blc.customerInterface.graphql.company.mutation.mapper.CompanyMapper;
import com.blc.customerInterface.graphql.company.service.CompanyService;
import com.blc.customerInterface.graphql.permission.domain.PermissionName;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAuthority('"+ PermissionName.CATEGORY_CREATE +"')")
    public Company createCompany(@Valid CompanyCreateInput input){
        return companyService.save(companyMapper.toEntity(input));
    }

    @PreAuthorize("hasAuthority('"+PermissionName.CATEGORY_UPDATE +"')")
    public Company updateCompany(UUID id, @Valid CompanyUpdateInput input){
        return companyService.findById(id).map(company ->companyService
                .update(companyMapper.updateEntity(company,input))).orElseThrow(RuntimeException::new);
    }

    @PreAuthorize("hasAuthority('"+PermissionName.CATEGORY_DELETE +"')")
    public UUID deleteCompany(UUID id){
        return companyService.findById(id).map(companyService::delete).orElseThrow(RuntimeException::new);
    }

    @PreAuthorize("hasAuthority('"+PermissionName.CATEGORY_UNDELETE +"')")
    public Company undeleteCompany(UUID id){
        return companyService.findById(id).map(companyService::undelete).orElseThrow(RuntimeException::new);
    }


}
