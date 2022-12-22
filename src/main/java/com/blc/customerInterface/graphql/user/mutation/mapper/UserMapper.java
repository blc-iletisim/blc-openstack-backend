package com.blc.customerInterface.graphql.user.mutation.mapper;

import com.blc.customerInterface.graphql.company.domain.Company;
import com.blc.customerInterface.graphql.company.service.CompanyService;
import com.blc.customerInterface.graphql.role.domain.Role;
import com.blc.customerInterface.graphql.role.service.RoleService;
import com.blc.customerInterface.graphql.user.domain.User;
import com.blc.customerInterface.graphql.user.mutation.input.UserCreateInput;
import com.blc.customerInterface.graphql.user.mutation.input.UserUpdateInput;
import com.blc.customerInterface.lib.dao.mutation.mapper.BaseCreateUpdateMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class UserMapper extends BaseCreateUpdateMapper<User, UserCreateInput, UserUpdateInput> {
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    private final CompanyService companyService;

    @Autowired
    public UserMapper(RoleService roleService, PasswordEncoder passwordEncoder, CompanyService companyService) {
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.companyService = companyService;
    }

    @Override
    public User toEntity(UserCreateInput input) throws Throwable {
        User entity = new User();
        entity.setName(input.getName());
        entity.setEmail(input.getEmail());
        entity.setPassword(passwordEncoder.encode(input.getPassword()));
        Company company = companyService.findById(input.getCompany()).orElseThrow(new Supplier<Throwable>() {
            @Override
            public Throwable get() {
                return new RuntimeException();
            }
        });
        entity.setCompany(company);
        Role role = roleService.findById(input.getRole()).orElse(null);
        entity.setRole(role);
        return entity;
    }

    @Override
    public User updateEntity(User entity, UserUpdateInput input) throws Throwable {
        entity.setName(input.getName());
        entity.setEmail(input.getEmail());
        entity.setPassword(passwordEncoder.encode(input.getPassword()));
        Company company = companyService.findById(input.getCompany()).orElseThrow(new Supplier<Throwable>() {
            @Override
            public Throwable get() {
                return new RuntimeException();
            }
        });
        entity.setCompany(company);
        Role role = roleService.findById(input.getRole()).orElse(null);
        entity.setRole(role);
        return entity;
    }
}
