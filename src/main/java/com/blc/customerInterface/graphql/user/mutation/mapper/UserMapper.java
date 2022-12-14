package com.blc.customerInterface.graphql.user.mutation.mapper;

import com.blc.customerInterface.graphql.role.domain.Role;
import com.blc.customerInterface.graphql.role.service.RoleService;
import com.blc.customerInterface.graphql.user.domain.User;
import com.blc.customerInterface.graphql.user.mutation.input.UserCreateInput;
import com.blc.customerInterface.graphql.user.mutation.input.UserUpdateInput;
import com.blc.customerInterface.lib.dao.mutation.mapper.BaseCreateUpdateMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends BaseCreateUpdateMapper<User, UserCreateInput, UserUpdateInput> {
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserMapper(RoleService roleService, PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User toEntity(UserCreateInput input)  {
        User entity = new User();
        entity.setName(input.getName());
        entity.setEmail(input.getEmail());
        entity.setPassword(passwordEncoder.encode(input.getPassword()));
        entity.setCompany(input.getCompany());
        Role role = roleService.findById(input.getRole()).orElse(null);
        entity.setRole(role);
        return entity;
    }

    @Override
    public User updateEntity(User entity, UserUpdateInput input)  {
        entity.setName(input.getName());
        entity.setEmail(input.getEmail());
        entity.setPassword(passwordEncoder.encode(input.getPassword()));
        entity.setCompany(input.getCompany());
        Role role = roleService.findById(input.getRole()).orElse(null);
        entity.setRole(role);
        return entity;
    }
}
