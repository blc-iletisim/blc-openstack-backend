package com.blc.customerInterface.graphql.role.mutation.mapper;

import com.blc.customerInterface.graphql.permission.domain.Permission;
import com.blc.customerInterface.graphql.permission.service.PermissionService;
import com.blc.customerInterface.graphql.role.domain.Role;
import com.blc.customerInterface.graphql.role.mutation.input.RoleCreateInput;
import com.blc.customerInterface.graphql.role.mutation.input.RoleUpdateInput;
import com.blc.customerInterface.lib.dao.mutation.mapper.BaseCreateUpdateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class RoleMapper extends BaseCreateUpdateMapper<Role, RoleCreateInput, RoleUpdateInput> {
    private final PermissionService permissionService;

    @Autowired
    public RoleMapper(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Override
    public Role toEntity(RoleCreateInput input)  {
        Role entity = new Role();
        entity.setName(input.getName());
        Set<Permission> permissions = new HashSet<>();
        for (int i =0; i<input.getPermissions().size();i++){
            Permission permission = permissionService.findById(input.getPermissions().get(i)).orElse(null);
            permissions.add(permission);
        }
        entity.setPermissions(permissions);
        return entity;
    }

    @Override
    public Role updateEntity(Role entity, RoleUpdateInput input){
        entity.setName(input.getName());
        Set<Permission> permissions = new HashSet<>();
        for (int i =0; i<input.getPermissions().size();i++){
            Permission permission = permissionService.findById(input.getPermissions().get(i)).orElse(null);
            permissions.add(permission);
        }
        entity.setPermissions(permissions);
        return entity;
    }


}
