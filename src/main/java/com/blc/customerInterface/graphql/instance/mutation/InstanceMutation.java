package com.blc.customerInterface.graphql.instance.mutation;

import com.blc.customerInterface.graphql.instance.domain.Instance;
import com.blc.customerInterface.graphql.instance.mutation.input.InstanceCreateInput;
import com.blc.customerInterface.graphql.instance.mutation.input.InstanceUpdateInput;
import com.blc.customerInterface.graphql.instance.mutation.mapper.InstanceMapper;
import com.blc.customerInterface.graphql.instance.service.InstanceService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.UUID;

@Component
@Validated
public class InstanceMutation implements GraphQLMutationResolver {
    private final InstanceService instanceService;
    private final InstanceMapper instanceMapper;

    @Autowired
    public InstanceMutation(InstanceService instanceService, InstanceMapper instanceMapper) {
        this.instanceService = instanceService;
        this.instanceMapper = instanceMapper;
    }
    public Instance createInstance(@Valid InstanceCreateInput input){
        return instanceService.save(instanceMapper.toEntity(input));
    }
    public Instance updateInstance(UUID id, @Valid InstanceUpdateInput input){
        return instanceService.findById(id).map(instance ->instanceService.update(instanceMapper.updateEntity(instance,input))).orElseThrow(RuntimeException::new);
    }
    public UUID deleteInstance(UUID id){
        return instanceService.findById(id).map(instanceService::delete).orElseThrow(RuntimeException::new);
    }
    public Instance undeleteInstance(UUID id){
        return instanceService.findById(id).map(instanceService::undelete).orElseThrow(RuntimeException::new);
    }
}
