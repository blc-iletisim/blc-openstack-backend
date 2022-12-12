package com.blc.customerInterface.graphql.company.mutation.mapper;

import com.blc.customerInterface.graphql.company.domain.Company;
import com.blc.customerInterface.graphql.company.mutation.input.CompanyCreateInput;
import com.blc.customerInterface.graphql.company.mutation.input.CompanyUpdateInput;
import com.blc.customerInterface.lib.dao.mutation.mapper.BaseCreateUpdateMapper;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper extends BaseCreateUpdateMapper<Company, CompanyCreateInput, CompanyUpdateInput> {



    @Override
    public Company toEntity(CompanyCreateInput input)  {
        Company entity = new Company();
        entity.setName(input.getName());

        return entity;
    }

    @Override
    public Company updateEntity(Company entity, CompanyUpdateInput input)  {

        entity.setName(input.getName());

        return entity;
    }
}
