package com.blc.customerInterface.graphql.user.repo.critera;

import com.blc.customerInterface.lib.dao.repo.criteria.BaseCriteria;

public class UserCriteria extends BaseCriteria {
    private String name;
    private String email;


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

}
