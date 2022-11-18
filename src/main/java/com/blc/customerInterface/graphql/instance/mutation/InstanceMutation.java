package com.blc.customerInterface.graphql.instance.mutation;

import com.blc.customerInterface.graphql.instance.domain.Instance;
import com.blc.customerInterface.graphql.instance.mutation.input.InstanceCreateInput;
import com.blc.customerInterface.graphql.instance.mutation.input.InstanceUpdateInput;
import com.blc.customerInterface.graphql.instance.mutation.mapper.InstanceMapper;
import com.blc.customerInterface.graphql.instance.service.InstanceService;
import com.blc.customerInterface.graphql.permission.domain.PermissionName;
import com.blc.customerInterface.jwt.JwtTokenProvider;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAuthority('"+ PermissionName.INSTANCE_CREATE +"')")
    public Instance createInstance(@Valid InstanceCreateInput input, DataFetchingEnvironment environment) throws Throwable{
        UUID userId = UUID.fromString(JwtTokenProvider.getAttribute(environment,"userId"));
        input.setUser(userId);
        return instanceService.save(instanceMapper.toEntity(input));
    }
    @PreAuthorize("hasAuthority('"+PermissionName.INSTANCE_UPDATE +"')")
    public Instance updateInstance(UUID id, @Valid InstanceUpdateInput input) throws Throwable{
        return instanceService.findById(id).map(instance ->instanceService.update(instanceMapper.updateEntity(instance,input))).orElseThrow(RuntimeException::new);
    }
    @PreAuthorize("hasAuthority('"+PermissionName.INSTANCE_DELETE +"')")
    public UUID deleteInstance(UUID id){
        return instanceService.findById(id).map(instanceService::delete).orElseThrow(RuntimeException::new);
    }
    @PreAuthorize("hasAuthority('"+PermissionName.INSTANCE_UNDELETE +"')")
    public Instance undeleteInstance(UUID id){
        return instanceService.findById(id).map(instanceService::undelete).orElseThrow(RuntimeException::new);
    }
}
