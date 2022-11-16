package com.blc.customerInterface.graphql.role.mutation;

import com.blc.customerInterface.graphql.role.domain.Role;
import com.blc.customerInterface.graphql.role.mutation.input.RoleCreateInput;
import com.blc.customerInterface.graphql.role.mutation.input.RoleUpdateInput;
import com.blc.customerInterface.graphql.role.mutation.mapper.RoleMapper;
import com.blc.customerInterface.graphql.role.service.RoleService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.UUID;

@Validated
@Component
public class RoleMutation implements GraphQLMutationResolver {
    private final RoleService roleService;
    private final RoleMapper roleMapper;

    @Autowired
    public RoleMutation(RoleService roleService, RoleMapper roleMapper) {
        this.roleService = roleService;
        this.roleMapper = roleMapper;
    }
    public Role createRole(@Valid RoleCreateInput input){
        return roleService.save(roleMapper.toEntity(input));
    }

    public Role updateRole(UUID id, @Valid RoleUpdateInput input){
        return roleService.findById(id).map(role -> roleService.update(roleMapper.updateEntity(role,input))).orElseThrow(RuntimeException::new);
    }
    public UUID deleteRole(UUID id){
        return roleService.findById(id).map(roleService::delete).orElseThrow(RuntimeException::new);
    }
    public Role undeleteRole(UUID id){
        return roleService.findById(id).map(roleService::undelete).orElseThrow(RuntimeException::new);
    }
}
