package com.blc.customerInterface.graphql.user.mutation.input;

import com.blc.customerInterface.lib.dao.mutation.input.BaseUpdateInput;

import java.util.UUID;

public class UserUpdateInput extends BaseUpdateInput {

    private String name;
    private String email;
    private String password;
    private UUID role;
    private String company;


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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
