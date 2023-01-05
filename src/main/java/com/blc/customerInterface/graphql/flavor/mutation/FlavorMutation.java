package com.blc.customerInterface.graphql.flavor.mutation;

import com.blc.customerInterface.graphql.flavor.domain.Flavor;
import com.blc.customerInterface.graphql.flavor.mutation.input.FlavorCreateInput;
import com.blc.customerInterface.graphql.flavor.mutation.input.FlavorUpdateInput;
import com.blc.customerInterface.graphql.flavor.mutation.mapper.FlavorMapper;
import com.blc.customerInterface.graphql.flavor.service.FlavorService;
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
public class FlavorMutation implements GraphQLMutationResolver {

    private final FlavorService flavorService;
    private final FlavorMapper flavorMapper;

    @Autowired
    public FlavorMutation(FlavorService flavorService, FlavorMapper flavorMapper) {
        this.flavorService = flavorService;
        this.flavorMapper = flavorMapper;
    }
   // @PreAuthorize("hasAuthority('"+ PermissionName.FLAVOR_CREATE +"')")
    public Flavor createFlavor(@Valid FlavorCreateInput input){
        return flavorService.save(flavorMapper.toEntity(input));
    }
    @PreAuthorize("hasAuthority('"+ PermissionName.FLAVOR_UPDATE +"')")
    public Flavor updateFlavor(UUID id, @Valid FlavorUpdateInput input){
        return flavorService.findById(id).map(flavor ->flavorService.update(flavorMapper.updateEntity(flavor,input))).orElseThrow(RuntimeException::new);
    }
    @PreAuthorize("hasAuthority('"+ PermissionName.FLAVOR_DELETE +"')")
    public UUID deleteFlavor(UUID id){
        return flavorService.findById(id).map(flavorService::delete).orElseThrow(RuntimeException::new);
    }
    @PreAuthorize("hasAuthority('"+ PermissionName.FLAVOR_UNDELETE +"')")
    public Flavor undeleteFlavor(UUID id){
        return flavorService.findById(id).map(flavorService::undelete).orElseThrow(RuntimeException::new);
    }
}
