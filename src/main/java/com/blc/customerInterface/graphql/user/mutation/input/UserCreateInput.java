package com.blc.customerInterface.graphql.user.mutation.input;

import com.blc.customerInterface.lib.dao.mutation.input.BaseCreateInput;

import java.util.UUID;


public class UserCreateInput extends BaseCreateInput {

    private String name;
    private String email;
    private String password;
    private UUID role;
    private UUID company;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getRole() {
        return role;
    }

    public void setRole(UUID role) {
        this.role = role;
    }

    public UUID getCompany() {
        return company;
    }

    public void setCompany(UUID company) {
        this.company = company;
    }
}
