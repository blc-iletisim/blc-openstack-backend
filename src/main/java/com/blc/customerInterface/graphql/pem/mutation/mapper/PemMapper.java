package com.blc.customerInterface.graphql.pem.mutation.mapper;

import com.blc.customerInterface.graphql.pem.domain.Pem;
import com.blc.customerInterface.graphql.pem.mutation.input.PemCreateInput;
import com.blc.customerInterface.graphql.pem.mutation.input.PemUpdateInput;
import com.blc.customerInterface.lib.dao.mutation.mapper.BaseCreateUpdateMapper;
import org.springframework.stereotype.Component;

@Component
public class PemMapper extends BaseCreateUpdateMapper<Pem, PemCreateInput, PemUpdateInput> {
    @Override
    public Pem toEntity(PemCreateInput input) {
        Pem entity = new Pem();
        entity.setName(input.getName());
        entity.setPem_url(input.getPem_url());
        return entity;
    }

    @Override
    public Pem updateEntity(Pem entity, PemUpdateInput input){
        entity.setName(input.getName());
        entity.setPem_url(input.getPem_url());
        return entity;
    }
}
