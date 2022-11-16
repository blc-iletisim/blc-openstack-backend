package com.blc.customerInterface.graphql.flavor.mutation.mapper;

import com.blc.customerInterface.graphql.flavor.domain.Flavor;
import com.blc.customerInterface.graphql.flavor.mutation.input.FlavorCreateInput;
import com.blc.customerInterface.graphql.flavor.mutation.input.FlavorUpdateInput;
import com.blc.customerInterface.lib.dao.mutation.mapper.BaseCreateUpdateMapper;
import org.springframework.stereotype.Component;

@Component
public class FlavorMapper extends BaseCreateUpdateMapper<Flavor, FlavorCreateInput, FlavorUpdateInput> {
    @Override
    public Flavor toEntity(FlavorCreateInput input)  {
        Flavor entity= new Flavor();
        entity.setName(input.getName());
        entity.setCpu_size(input.getCpu_size());
        entity.setRam_size(input.getRam_size());
        entity.setRoot_disk(input.getRoot_disk());
        return entity;
    }

    @Override
    public Flavor updateEntity(Flavor entity, FlavorUpdateInput input)  {
        entity.setName(input.getName());
        entity.setCpu_size(input.getCpu_size());
        entity.setRam_size(input.getRam_size());
        entity.setRoot_disk(input.getRoot_disk());
        return entity;
    }
}
